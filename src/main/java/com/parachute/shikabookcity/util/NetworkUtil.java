package com.parachute.shikabookcity.util;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.parachute.shikabookcity.config.CustomObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Map;


/**
 *
 * 常用获取客户端信息的工具
 *
 * @author machi
 * @date 2022/09/14
 */
@Slf4j
public final class NetworkUtil {
    private NetworkUtil(){}
    /**
     * 得到现在ip4
     *
     * @return {@link String}
     * @throws IOException ioexception
     */ // 方法4
    private static String getNowIP() throws IOException {
        String ip = null;
        String objWebURL = "https://bajiu.cn/ip/";
        URL url = new URL(objWebURL);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String s = "";
            while ((s = br.readLine()) != null) {
                if (s.contains("互联网IP")) {
                    ip = s.substring(s.indexOf("'") + 1, s.lastIndexOf("'"));
                    break;
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        if (StringUtils.isEmpty(ip)) {
            throw new RuntimeException("获取失败");
        }
        return ip;
    }

    public static Map<String, String> getIPBody(RestTemplate restTemplate, CustomObjectMapper objectMapper) throws IOException {

        String nowIP = getNowIP();
        ResponseEntity<String> result = restTemplate.getForEntity("https://whois.pconline.com.cn/ipJson.jsp?ip=" + nowIP + "&json=true", String.class);

        String body = result.getBody();
        return objectMapper.readValue(body, new TypeReference<Map<String, String>>() {
        });
    }
}