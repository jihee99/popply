package com.ex.popply.event.model.dto.request;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateEventRequest {

	@Schema(defaultValue = "1", description = "매니저 고유 아이디")
	@Positive
	private Long userId;

	@Schema(defaultValue = "테스트용 이벤트명", description = "이벤트명")
	@NotBlank(message = "이벤트 명을 입력하세요.")
	@Length(max = 25)
	private String name;

	@Schema(
		type = "string",
		pattern = "yyyy.MM.dd HH:mm",
		defaultValue = "2024.08.01 10:00",
		description = "시작 시각")
	@NotNull(message = "시작일을 입력하세요.")
	@Future(message = "시작일은 현재보다 이후여야 합니다.")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime startAt;

	@Schema(defaultValue = "30", description = "소요시간")
	@Positive(message = "예상 소요시간(분)을 입력하세요.")
	private Long runTime;

	@Schema(defaultValue = "30", description = "시간당 입장제한인원")
	@Positive(message = "시간 당 입장 제한 인원을 입력하세요.")
	private Long limitPerHour;

}
