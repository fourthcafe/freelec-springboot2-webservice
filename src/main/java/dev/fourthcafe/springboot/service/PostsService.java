package dev.fourthcafe.springboot.service;

import dev.fourthcafe.springboot.domain.posts.Posts;
import dev.fourthcafe.springboot.domain.posts.PostsRepository;
import dev.fourthcafe.springboot.web.dto.PostsResponseDto;
import dev.fourthcafe.springboot.web.dto.PostsSaveRequestDto;
import dev.fourthcafe.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
	private final PostsRepository postsRepository;


	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}


	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		final Posts posts = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
		posts.update(requestDto.getTitle(), requestDto.getContent());

		return id;
	}


	public PostsResponseDto findById(Long id) {
		final Posts entity = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

		return new PostsResponseDto(entity);
	}
}
