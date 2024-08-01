package com.ex.popply.event;

import com.ex.popply.event.model.Event;
import com.ex.popply.event.model.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByUserId(Long userId);

    List<Event> findAllByUserIdIn(List<Long> userId);

    List<Event> queryEventsByUserIdIn(List<Long> userId);

    List<Event> queryEventsByStatus(EventStatus status);

    List<Event> findAllByOrderByCreatedAtDesc();

    List<Event> findAllByIdIn(List<Long> ids);
}
