package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * readme排序
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-08 16:12
 */
public class ReadmeSort {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        StringBuilder builder;
        try (BufferedReader br = new BufferedReader(new FileReader("README.md"))) {
            builder = new StringBuilder();

            for (int i = 0; i < 4; i++) {
                builder.append(br.readLine()).append("\n");
            }

            List<DataLine> lineList = new ArrayList<>();
            String temp;
            while ((temp = br.readLine()) != null) {
                DataLine dataLine = new DataLine(temp);
                lineList.add(dataLine);
            }

            Collections.sort(lineList);
            for (DataLine data : lineList) {
                builder.append(data).append("\n");
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("README.md", false))) {
            bw.write(builder.toString());
        }
    }

    static class DataLine implements Comparable {
        int squence;
        String problem;
        String level;
        String language;
        String tutorial = "";

        public DataLine(String line) {
            String[] arr = line.split("\\|");
            this.squence = Integer.parseInt(arr[1].trim());
            this.problem = arr[2];
            this.level = arr[3];
            this.language = arr[4];
            if(arr.length > 5) {
                this.tutorial = arr[5];
            }
        }

        @Override
        public String toString() {
            return "|" + squence + "|"
                    + problem + "|" + level + "|" + language + "|" + tutorial + "|";
        }

        @Override
        public int compareTo(Object o) {
            if(o == null) {
                throw new NullPointerException();
            }
            DataLine other = (DataLine) o;
            return this.squence - other.squence;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DataLine dataLine = (DataLine) o;
            return squence == dataLine.squence;
        }

        @Override
        public int hashCode() {
            return Objects.hash(squence);
        }

        public int getSquence() {
            return squence;
        }

        public void setSquence(int squence) {
            this.squence = squence;
        }

        public String getProblem() {
            return problem;
        }

        public void setProblem(String problem) {
            this.problem = problem;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getTutorial() {
            return tutorial;
        }

        public void setTutorial(String tutorial) {
            this.tutorial = tutorial;
        }

    }
}
