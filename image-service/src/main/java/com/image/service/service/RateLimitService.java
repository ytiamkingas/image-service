package com.image.service.service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;

@Service
public class RateLimitService {
	private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();
	
	/**
	 * Resolve (or create) a bucket for a given username.
	 * Example policy: 5 tokens per minute.
	 */
	
	public Bucket resolveBucket(String username) {
		return buckets.computeIfAbsent(username, key -> createNewBucket());
	}
	
	@SuppressWarnings("deprecation")
	private Bucket createNewBucket() {
		Bandwidth limit = Bandwidth.simple(5, Duration.ofMinutes(1));
		return Bucket4j.builder()
				.addLimit(limit)
				.build();
	}
	
	/**
	 * Optionally: allow resetting a user's bucket (for admin/testing).
	 */
	public void resetBucket(String username) {
		buckets.remove(username);
	}
}