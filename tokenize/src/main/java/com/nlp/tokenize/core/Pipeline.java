package com.nlp.tokenize.core;

import java.util.Properties;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Pipeline {
	
	private static Properties properties;
	private static String propertiesName = "tokenize, pos";
	private static StanfordCoreNLP stanfordcorenlp;
	
	private Pipeline() {
		
	}
	
	static {
		properties = new Properties();
		properties.setProperty("annotators", propertiesName);
	}
	
	@Bean(name = "stanfordcorenlp")
	public static StanfordCoreNLP getInstance() {
		if(stanfordcorenlp == null) {
			stanfordcorenlp = new StanfordCoreNLP(properties);
		}
		
		return stanfordcorenlp;
	}
}
