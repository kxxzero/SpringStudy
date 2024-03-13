package com.sist.main;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling_2 {
	private static void crawlJobDetails(String url) {
	    try {
	        Document document = Jsoup.connect(url).get();

	        // 채용 공고 제목 추출
	        Elements titles = document.select("#contents > div.careers-area > div.careers-private > p.title");
	        for (Element title : titles) {
	            System.out.println("채용 공고 제목: " + title.text());
	            System.out.println("------------------------");
	        }

	        // 나머지 코드는 주석 처리

	    } catch (IOException e) {
	        System.err.println("상세 정보 크롤링 중 오류 발생: " + e.getMessage());
	    }
	}
}
