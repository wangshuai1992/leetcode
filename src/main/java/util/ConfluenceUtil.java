package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;

public class ConfluenceUtil {

    public static final String RESPONSE_BODY = "body";
    public static final String RESPONSE_COOKIE = "cookie";
    private static final Logger log = LoggerFactory.getLogger(ConfluenceUtil.class);

    public static boolean createPageByFile(String baseUrl,String parentId,String spaceKey,
                                           String filePath,String username,String password){
        try{
            String author = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
            File file = new File(filePath);
            String fileName = Paths.get(filePath).getFileName().toString();
            String docTitle = fileName.split("\\.")[0];

            //创建目录
            String createPageUrl = baseUrl+"/rest/api/content";
            JSONObject data = new JSONObject();
            data.put("type", "page");
            data.put("title", docTitle);
            data.put("status", "current");
            JSONObject space = new JSONObject();
            space.put("key", spaceKey);
            data.put("space", space);
            if (StringUtils.isNotEmpty(parentId)) {
                JSONObject parent = new JSONObject();
                parent.put("id", Long.valueOf(parentId));
                data.put("ancestors", Collections.singletonList(parent));
            }

            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpPost request = new HttpPost(createPageUrl);
            request.setHeader("Authorization", "Basic " + author);
            request.setHeader("X-Atlassian-Token", "no-check");
            request.setHeader("Content-Type", "application/json;charset=utf-8");//设置httpPost的请求头中的MIME类型为json
            // Set request body
            StringEntity stringEntity = new StringEntity(data.toJSONString(), ContentType.APPLICATION_JSON);
            request.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
//            log.info("创建目录报文:{}",responseBody);
            System.out.println("创建目录报文:"+responseBody);
            String pageId = null;
            if(StringUtils.isNotEmpty(responseBody)){
                JSONObject cfResultO = JSONObject.parseObject(responseBody);
                String message = (String)cfResultO.get("message");
                if(!cfResultO.containsKey("id")){
//                    log.error("创建目录报错,详情:{}",message);
                    System.out.println("创建目录报错,详情:"+message);
                    return false;
                }
                pageId = (String) cfResultO.get("id");
            }else{
//                log.error("创建目录报错，无报文返回");
                System.out.println("创建目录报错，无报文返回");
                return false;
            }

            //导入文件到confluence
            String importFileUrl = baseUrl+"/pages/worddav/importword.action?pageId="+pageId;
            String atlToken = "c8b2523a83ac9193a8a940c65cfacf010e9531b511";

            Map<String, String> importHeaders = new LinkedHashMap<>();
            importHeaders.put("Authorization", "Basic " + author);
            importHeaders.put("X-Atlassian-Token", "no-check");
//            importHeaders.put("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary1oi1YwiBnALYWNNf; charset=utf-8");

            HttpEntity importRequestEntity = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addBinaryBody("filename", file, ContentType.DEFAULT_BINARY, fileName)
                    .addTextBody("atl_token", atlToken)
                    .addTextBody("submit", "下一步")
                    .build();
            Map<String,String> importResponseResult = httpRequest(importFileUrl,importHeaders,importRequestEntity);
            String importResponseBody = importResponseResult.get(ConfluenceUtil.RESPONSE_BODY);
            String cookie = importResponseResult.get(ConfluenceUtil.RESPONSE_COOKIE);
            if(StringUtils.isNotEmpty(importResponseBody)){
                boolean importResult = importResponseBody.contains("发生如下错误");
                if(importResult){
                    Document doc = Jsoup.parse(importResponseBody);
                    Elements errorMessages = doc.select(".aui-message.aui-message-error.closeable");

                    StringBuilder message = new StringBuilder();
                    for (Element errorMessage : errorMessages) {
                        message.append(errorMessage.text());
                    }
//                    log.error("导入文件失败,原因:{}",message.toString());
                    System.out.println("导入文件失败,原因:"+message.toString());
                    return false;
                }
            }else{
//                log.error("导入文件失败,原因:confluence端无报文返回");
                System.out.println("导入文件失败,原因:confluence端无报文返回");
                return false;
            }

            //确定导入配置&确定生成文档
            String createFileUrl = baseUrl+"/pages/worddav/doimportword.action";
            Map<String, String> createHeaders = new LinkedHashMap<>();
            createHeaders.put("Authorization", "Basic " + author);
            createHeaders.put("Cookie", cookie);
            createHeaders.put("X-Atlassian-Token", "no-check");
//            createHeaders.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            HttpEntity createRequestEntity = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addTextBody("atl_token", atlToken)
                    .addTextBody("pageId", pageId)
                    .addTextBody("treeDepth", "0")
                    .addTextBody("advanced", "true")
                    .addTextBody("docTitle", docTitle)
                    .addTextBody("importSpace", "false") //importSpace true:在一级目录上创建新的目录  false:替换原有目录的名称
                    .addTextBody("conflict", "1")
                    .addTextBody("submit", "导入")
                    .build();
            Map<String,String> createResponseResult = httpRequest(createFileUrl,createHeaders,createRequestEntity);
            String createResponseBody = createResponseResult.get(ConfluenceUtil.RESPONSE_BODY);
            if(StringUtils.isNotEmpty(createResponseBody)){
                boolean isCreateSuccess = createResponseBody.contains("发生如下错误");
                if(isCreateSuccess){
                    Document doc = Jsoup.parse(createResponseBody);
                    Elements errorMessages = doc.select(".aui-message.aui-message-error.closeable");
                    StringBuilder message = new StringBuilder();
                    for (Element errorMessage : errorMessages) {
                        message.append(errorMessage.text());
                    }
//                    log.error("confluence 创建文档失败，原因:{}",message.toString());
                    System.out.println("confluence 创建文档失败，原因:"+message.toString());
                }
            }

            //更新页面名称
            String renamePageUrl = baseUrl+"/rest/api/content/" + pageId;
            data = new JSONObject();
            data.put("type", "page");
            data.put("title", docTitle);
            data.put("version", JSON.parse("{\"number\":3}"));
            space = new JSONObject();
            space.put("key", spaceKey);
            data.put("space", space);

            HttpPut putRequest = new HttpPut(renamePageUrl);
            putRequest.setHeader("Authorization", "Basic " + author);
            putRequest.setHeader("X-Atlassian-Token", "no-check");
            putRequest.setHeader("Content-Type", "application/json;charset=utf-8");//设置httpPost的请求头中的MIME类型为json
            // Set putRequest body
            stringEntity = new StringEntity(data.toJSONString(), ContentType.APPLICATION_JSON);
            putRequest.setEntity(stringEntity);
            response = httpClient.execute(putRequest);
            entity = response.getEntity();
            String renameResponseBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            System.out.println("更新名称报文:"+renameResponseBody);

        }catch (Exception e){
//            log.error("程序报错:{}",e);
            System.out.println("程序报错:"+e);
        }
        return true;
    }

    public static Map<String,String> httpRequest(String url,Map<String, String> headers,HttpEntity requestEntity){
        Map<String,String> result = new HashMap<>();
        try{
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(url);
            headers.forEach(request::setHeader);
            request.setEntity(requestEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            // 获取返回的cookie
            if(null != response.getFirstHeader("Set-Cookie")){
                String cookie = response.getFirstHeader("Set-Cookie").getValue();
                result.put(ConfluenceUtil.RESPONSE_COOKIE,cookie);
            }

            result.put(ConfluenceUtil.RESPONSE_BODY,responseBody);
//            System.out.println("完成http请求, url: " + url + " , headers: " + JSON.toJSONString(headers) + " , requestEntity: " + requestEntity + " , responseBody: " + responseBody);
        }catch (Exception e){
//            log.error("程序报错:{}",e);
            System.out.println("程序报错:"+e);
        }
        return result;
    }

    public static void printAllSubPage() {
        // 从桌面读取文件
        File file = new File("D:\\Users\\wangshuai143\\Desktop\\111.json");
        String json = FileUtils.getFileContent(file);
        List<ConfluencePageVo> data = JSON.parseObject(json, new TypeReference<List<ConfluencePageVo>>() {});
        String pageTitle = "7 全球派遣 Global Mobility";
        //        String pageTitle = "5 薪酬福利";
        ConfluencePageVo pageVo = findPageByTitle(data, pageTitle);
        List<ConfluencePageVo> pageVos = new ArrayList<>();
        pageVos.add(pageVo);
        pageVos.addAll(getAllSubPage(pageVo));
        List<String> lines = new ArrayList<>();
        for (ConfluencePageVo vo : pageVos) {
            lines.add(vo.getTitle() + ":" + vo.getUrl());
        }
        //        Collections.sort(lines);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private static ConfluencePageVo findPageByTitle(List<ConfluencePageVo> data, String pageTitle) {
        if (CollectionUtils.isEmpty(data)) {
            return null;
        }
        for (ConfluencePageVo pageVo : data) {
            if (pageTitle.equals(pageVo.getTitle())) {
                return pageVo;
            }
            ConfluencePageVo page = findPageByTitle(pageVo.getChildrenPages(), pageTitle);
            if (page != null) {
                return page;
            }
        }
        return null;
    }

    private static List<ConfluencePageVo> getAllSubPage(ConfluencePageVo pageVo) {
        if (pageVo == null) {
            return new ArrayList<>();
        }
        List<ConfluencePageVo> children = pageVo.getChildrenPages();
        if (CollectionUtils.isEmpty(children)) {
            return new ArrayList<>();
        }
        List<ConfluencePageVo> allSubPage = new ArrayList<>();
        for (ConfluencePageVo child : children) {
            allSubPage.add(child);
            List<ConfluencePageVo> subPage = getAllSubPage(child);
            if (CollectionUtils.isNotEmpty(subPage)) {
                allSubPage.addAll(subPage);
            }
        }
        allSubPage.sort(Comparator.comparing(ConfluencePageVo::getTitle));
        return allSubPage;
    }

    public static void main(String[] args) {
//        String baseUrl = "https://321323.com";
//        String parentId = "230721489";
//        String spaceKey = "guiantest";
//        String username = "test2024";
//        String password = "321444";
//        String filePath = "D:\\1721987183472.docx";
//        createPageByFile(baseUrl,parentId,spaceKey,filePath,username,password);

//        String username = "323213";
//        String password = "121";
//        String author = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
//        System.out.println(author);

        printAllSubPage();
    }

}
