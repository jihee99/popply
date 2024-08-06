package com.ex.popply;

import com.ex.popply.event.EventRepository;
import com.ex.popply.event.model.Event;
import com.ex.popply.event.model.dto.request.CreateEventInfoRequest;
import com.ex.popply.event.model.dto.response.EventResponse;
import com.ex.popply.event.service.CreateEventUseCase;
import com.ex.popply.ticket.model.Ticket;
import com.ex.popply.ticket.model.dto.request.CreateTicketRequest;
import com.ex.popply.ticket.repository.TicketRepository;
import com.ex.popply.ticket.service.CreateTicketUseCase;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ex.popply.auth.repository.UserRepository;
import com.ex.popply.user.model.AccountRole;
import com.ex.popply.user.model.AccountState;
import com.ex.popply.user.model.User;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@RequiredArgsConstructor
@Component
public class Initializer implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CreateEventUseCase createEventUseCase;
    private final CreateTicketUseCase createTicketUseCase;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = User.builder()
                .email("test@test.com")
                .password(passwordEncoder.encode("1234"))
                .name("홍길동")
                .phoneNumber("010-1234-1234")
                .accountState(AccountState.NORMAL)
                .build();

		User user2 = User.builder()
			.email("manager@test.com")
			.password(passwordEncoder.encode("1234"))
			.name("홍길동")
			.phoneNumber("010-1234-1234")
			.accountState(AccountState.NORMAL)
			.accountRole(AccountRole.MANAGER)
			.build();

        userRepository.save(user);
        userRepository.save(user2);

        // 시큐리티 컨텍스트 설정
        Authentication auth = new UsernamePasswordAuthenticationToken(
                user2.getEmail(),
                null,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_MANAGER"))
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        CreateEventInfoRequest createEventInfoRequest = new CreateEventInfoRequest(
                "테스트용 이벤트",
                user2.getUserId(),
                "이벤트 등록 테스트입니다.",
                LocalDate.parse("2024.08.30", formatter),
                LocalTime.parse("10:00"),
                LocalTime.parse("19:00"),
                60L,
                30L,
                7L
        );

        EventResponse event = createEventUseCase.execute(createEventInfoRequest);

        CreateTicketRequest createTicketRequest =
                CreateTicketRequest.builder()
                        .name("테스트용티켓발행")
                        .description("티켓설명")
                        .price(0L)
                        .supplyCount(30L)
                        .isQuantityPublic(false)
                        .purchaseLimit(1L)
                        .build();

        createTicketUseCase.execute(createTicketRequest, event.getEventId());
    }
}
