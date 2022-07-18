package com.nlp.tokenize.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@RestController
@RequestMapping("/nlp")
public class myController {
	
	String userinfo; //to store the info related to user
	String action; //to store the info related to actions to be performed using lemma
	String memberID;// to store the id of the user retrived from data base
	String lemma;
	
	@Autowired
	private StanfordCoreNLP stanfordcorenlp;
	
	@PostMapping("/nlp/tokenize")
	public List<String> str(@RequestBody final String input){
		CoreDocument coreDocument = new CoreDocument(input);
		stanfordcorenlp.annotate(coreDocument);
		List<CoreLabel> coreLabels = coreDocument.tokens();
		
		for(CoreLabel coreLabel : coreLabels) {
			//printing the all the tokens
			String pos = coreLabel.getString(CoreAnnotations.PartOfSpeechAnnotation.class);
			String lemma = coreLabel.lemma();
			System.out.println(coreLabel.originalText());
			if(pos=="NNP")//NNP = proper noun 
			{
				userinfo = coreLabel.originalText();
			}
			if(lemma == "appointment") // here we match different action previously done to define the actions 
			{
				//using switch case based on lemma
				action = lemma;//here we use this statement to define the action to be performed  
			}
				
		}
		
		//use getmapping to reterive the info on memberid
		memberID = userinfo;
		
		System.out.println("Output: " + lemma + memberID);
		return null;
	}

}
