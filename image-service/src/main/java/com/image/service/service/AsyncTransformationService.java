package com.image.service.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.image.service.entity.Image;

@Service
public class AsyncTransformationService {
	@Autowired
	private ImageService imageService;
	
	@SuppressWarnings("unchecked")
	@Async("imageTaskExecutor")
	public CompletableFuture<Image> processImageAsync(Long imageId, Map<String, Object> transformations) throws IOException {
		Integer resizeWidth = null;
		Integer resizeHeight = null;
		Double rotate = null;
		Boolean grayscale = null;
		String format = null;
		
		if (transformations.containsKey("resize")) {
			Map<String, Integer> resize = (Map<String, Integer>) transformations.get("resize");
			resizeWidth = resize.get("width");
			resizeHeight = resize.get("height");
		}
		
		if (transformations.containsKey("rotate")) {
			rotate = ((Number) transformations.get("rotate")).doubleValue();
		}
		
		if (transformations.containsKey("filters")) {
			Map<String, Boolean> filters = (Map<String, Boolean>) transformations.get("filters");
			grayscale = filters.get("grayscale");
		}
		
		if (transformations.containsKey("format")) {
			format = (String) transformations.get("format");
		}
		
		Image transformed = imageService.transformImage(imageId, resizeWidth, resizeHeight, rotate, grayscale, format);
		return CompletableFuture.completedFuture(transformed);
	}
}
