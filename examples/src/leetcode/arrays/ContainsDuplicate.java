package leetcode.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andre Ticona.
 * Date: 4/5/2023
 * https://leetcode.com/problems/contains-duplicate/
 *
 *
 * Input: nums = [1,2,3,1]
 * Output: true
 *
 * Input: nums = [1,2,3,4]
 * Output: false
 *
 * Input: nums = [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 */
public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        Set mySet = new HashSet<>();
        for(int number : nums) {
            if(mySet.contains(number))
                return true;
            mySet.add(number);
        }
        return false;
    }
}
