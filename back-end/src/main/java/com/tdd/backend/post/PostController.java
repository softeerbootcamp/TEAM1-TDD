package com.tdd.backend.post;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.backend.post.data.SharingDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;
	//TODO : Response 만들
	//TODO : 로그인 검증 필요
	@PostMapping("/sharing")
	@Operation(summary = "차량 공유하기 요청", description = "차량 공유하기 시 Post 정보가 insert 되어야 합니다.")
	@ApiResponse(responseCode = "302", description = "메인 페이지로 REDIRECT")
	public ResponseEntity<Void> shares(@RequestBody @Valid SharingDto sharingDto) {
		// 요청: 차 이름, 위치, 옵션들, 동승여부, 운전경력, 사용자 id , 날짜 리스트,  요구사항
		// 302 REDIRECT index.html
		postService.save(sharingDto);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/"));
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}

	@GetMapping("/test-driving/locations")
	@Operation(summary = "시승가능한 차량 요청", description = "차종과 선택한 옵션 리스트를 요청 받으면 해당하는 Post의 location 리스트로 응답해야 함.")
	public void getLocations() {
		// 요청: 차 이름, 옵션 리스트
		// 응답 : location 리스트 (postId, locationX, locationY)
	}

	@GetMapping("/test-driving/{postId}")
	@Operation(summary = "location 리스트에서 하나의 포스트 선택 시에 대한 요청", description = "포스트의 해당 차종과 옵션 목록으로 응답해야 함.")
	public void getPost(@PathVariable Long postId) {
		// 응답: 차량 이름, 옵션목록
	}

	@GetMapping("/test-driving/appointments/{postId}")
	@Operation(summary = "해당 Post의 예약현황(날짜) 요청", description = "postId에 해당하는 포스트가 가진 Appointment 리스트로 응답해야 함.")
	public void getAppointments(@PathVariable Long postId) {
		// 응답 :  appointment 리스트
	}

	@PatchMapping("/appointments/{appointmentId}")
	@Operation(summary = "최종적인 예약 요청", description = "시승하기에 대한 사용자의 최종적인 요청으로 Appointment의 상태를 승낙으로 Update해야 함.")
	public void requestAppointment(@PathVariable Long appointmentId) {
		// 요청 : appointment id
	}

}
