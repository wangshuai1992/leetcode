package code;

/**
 * https://leetcode.com/problems/exchange-seats/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-27 15:32
 */
public class ExchangeSeats {

    // Solution 1
    //
    //  select
    //    (case
    //        when mod(id, 2) != 0 and counts != id then id + 1
    //        when mod(id, 2) != 0 and counts = id then id
    //        else id - 1
    //     end
    //    ) as id,
    //    student
    //  from
    //    seat,
    //    (
    //        select neighborCount(1) as counts from seat
    //    ) as seat_counts
    //  order by id asc;

    // Solution 2
    //
    //  select
    //    s1.id, coalesce(s2.student, s1.student) as student
    //  from
    //    seat s1
    //        left join
    //    seat s2
    //        on ((s1.id + 1) ^ 1) - 1 = s2.id
    //  order by s1.id;
}
