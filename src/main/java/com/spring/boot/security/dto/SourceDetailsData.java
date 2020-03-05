package com.spring.boot.security.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.SourceDetails;
import com.spring.boot.security.repository.SourceDetailsRepository;

@Service
public class SourceDetailsData {

	@Autowired
	SourceDetailsRepository sourceDetailsRepository;
	
	public List<SourceDetails> getAllSource()
	{
		return sourceDetailsRepository.findAll();
	}
	
	public SourceDetails saveSource(SourceDetails sourceDetails)
	{
		return sourceDetailsRepository.save(sourceDetails);
	}
	
	public String getNameBySource(String sourceName)
	{
		String source=null;
		if(sourceDetailsRepository.findBySourceName(sourceName)!=null)
			source=sourceDetailsRepository.findBySourceName(sourceName).getSourceName();
		return source;
	}
}
