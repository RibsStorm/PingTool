package com.kusofan.demo.fivegen.util;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PingTools {

    private static final String TAG = "PingTools";
//    private static String command = "ping -c 5 -w 3 ";
    private static String command = "";

    public static PingResult ping(String host,String count) {
        if (TextUtils.isEmpty(command)){
            command = "ping -c "+count+" ";
        }
        PingResult result = new PingResult();
        result.host = host;
        try {
            Process process = Runtime.getRuntime().exec(command + host);
            result.success = true;
            InputStreamReader reader = new InputStreamReader(process.getInputStream());
            BufferedReader br = new BufferedReader(reader);
            List<String> echo = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                echo.add(line);
            }
            PingResultParser.parse(result, echo);
        } catch (Exception e) {
            e.printStackTrace();
            result.success = false;
            result.avg_time = "失败";
        }
        return result;
    }

    public static class PingResult {
        public String host;
        public boolean success;
        public String min_time;
        public String max_time;
        public String avg_time;
        public List<String> times = new ArrayList<>();
    }

    private static class PingResultParser {

        static void parse(PingResult result, List<String> echo) {
            for (String line : echo) {
                //ping的内容
                if (line.contains("icmp_seq=") && line.contains("ttl=") && line.contains("time=")) {
                    String[] block = line.split(" ");
                    String timeStr = block[6].split("=")[1];
                    result.times.add(timeStr);

                    Log.d(TAG, "IP:" + result.host + ",耗时为:" + timeStr + " ms");
                }

                //最后结果
                if (line.contains("rtt min/avg/max/mdev")) {
                    String timeResult = line.split("=")[1];
                    String[] split = timeResult.split("/");

                    result.min_time = split[0];
                    result.avg_time = split[1];
                    result.max_time = split[2];
                }
            }
        }

    }

    public static void recycler(){
        command = "";
    }
}
