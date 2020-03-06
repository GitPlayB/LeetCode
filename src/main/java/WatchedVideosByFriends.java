import com.sun.org.apache.xpath.internal.operations.Bool;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 1311. 获取你好友已观看的视频
 * --------------------------------------------
 * 有 n 个人，每个人都有一个  0 到 n-1 的唯一 id 。
 * 给你数组 watchedVideos  和 friends ，其中 watchedVideos[i]  和 friends[i] 分别表示 id = i 的人观看过的视频列表和他的好友列表。
 * Level 1 的视频包含所有你好友观看过的视频，level 2 的视频包含所有你好友的好友观看过的视频，以此类推。一般的，Level 为 k 的视频包含所有从你出发，最短距离为 k 的好友观看过的视频。
 * 给定你的 id  和一个 level 值，请你找出所有指定 level 的视频，并将它们按观看频率升序返回。如果有频率相同的视频，请将它们按名字字典序从小到大排列。
 *---------------------------------------------
 *
 * [["bjwtssmu"],["aygr","mqls"],["vrtxa","zxqzeqy","nbpl","qnpl"],["r","otazhu","rsf"],["bvcca","ayyihidz","ljc","fiq","viu"]]
 * [[3,2,1,4],[0,4],[4,0],[0,4],[2,3,1,0]]
 * 3
 * 1
 */
public class WatchedVideosByFriends {
  public static void main(String[] args) {
    Solution1311 testCase = new Solution1311();

    List<String> s1 = Arrays.asList("hkpv","hwnkrtxu","uhbcy","fqyzgj");
    List<String> s2 = Arrays.asList("fwwxot","dt","feingsx");
    List<String> s3 = Arrays.asList("vmwulmqf");
    List<String> s4 = Arrays.asList("zi");
//    List<String> s5 = Arrays.asList("bvcca", "ayyihidz", "ljc", "fiq", "viu");


    List<List<String>> watchedVideos = Arrays.asList(s1, s2, s3, s4);

    int[][] friends = {{2,3},{3,2},{0,1},{1,0}};
    int id = 0;
    int level = 2;
    List<String> result = testCase.watchedVideosByFriends(watchedVideos, friends, id, level);
    System.out.println(result.toString());
  }
}

class Solution1311 {
  public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
    Queue<Integer> queue = new LinkedList<>();   // 使用队列存放找到的level为入参的朋友
    queue.offer(id);  // 从当前（id号选手）开始寻找朋友关系，直到第level层
    boolean[] visited = new boolean[friends.length];    // bool数组记录是否访问过
    visited[id] = true;

    /*
    * 找朋友。广度优先搜索。
    * 将朋友依次放入队列中，通过friends数组，
    * 依次把朋友的朋友找到，一直到第level层
     */
    for (int i = 0; i < level; i++) {
      int s = queue.size();
      for (int j = 0; j < s; j++) {
        Integer item = queue.poll();
        for (int each: friends[item]) {
          if (!visited[each]) {
            queue.offer(each);
            visited[each] = true;
          }
        }
      }
    }

    Map<String, Integer> freq = new HashMap<>();

    for (Integer item: queue) {
      List<String> videos = watchedVideos.get(item);
      for (String video: videos) {
        freq.put(video, (freq.containsKey(video)) ? freq.get(video)+1 : 1);
      }
    }

    ArrayList<Map.Entry<String, Integer>> freqList = new ArrayList<Map.Entry<String, Integer>>(freq.entrySet());
    Collections.sort(freqList, (o1, o2) -> (o2.getValue() - o1.getValue()));

    Map<String, Integer> sorted = freqList
        .stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    List<String> result = new ArrayList<>();
    for (Map.Entry<String, Integer> entry: sorted.entrySet()) {
      result.add(entry.getKey());
    }
    Collections.sort(result);
    return result;
  }
}
