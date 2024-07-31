package com.rubypaper.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rubypaper.guest.GuestVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RestController {

    public RestController() {
        System.out.println("==> RestController");
    }

    @GetMapping("/list")
    public String list(Model model, HttpServletRequest request, GuestVO vo) throws Exception {
        String BASE_URL = "http://127.0.0.1:8899/Restful/list";
        String RESULT_TYPE = "json";

        int start = request.getParameter("start") != null ? Integer.parseInt(request.getParameter("start")) : 0;
        int pageSize = 10;

        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        urlBuilder.append("?" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(RESULT_TYPE, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("start", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(start), "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pageSize", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pageSize), "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        System.out.println(sb);

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());

            String totalCount = String.valueOf(jsonObject.get("totalCount"));
            int totalPage = (int) Math.ceil(Integer.parseInt(totalCount) / (double) pageSize);

            JSONArray infoArray = (JSONArray) jsonObject.get("li");

            System.out.println("* items *");

            List<GuestVO> li = new ArrayList<GuestVO>();

            for (int i = 0; i < infoArray.size(); i++) {
                GuestVO m = new GuestVO();

                JSONObject object = (JSONObject) infoArray.get(i);

                m.setIdx(Integer.parseInt(String.valueOf(object.get("idx"))));
                m.setName(String.valueOf(object.get("name")));
                m.setMemo(String.valueOf(object.get("memo")));
                m.setAge(Integer.parseInt(String.valueOf(object.get("age"))));
                m.setRegdate(String.valueOf(object.get("regdate")));
                m.setI(start + i);  // 각 레코드의 전역 순서 번호를 설정합니다.
                li.add(m);
                System.out.println("성공 ==>" + i + ":" + m);
            }

            model.addAttribute("start", start);
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("totalCount", totalCount);
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("li", li);
            model.addAttribute("currentPage", start / pageSize + 1);

            System.out.println("li확인" + totalPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }
}