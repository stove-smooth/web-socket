package com.example.websocket.service;

import com.example.websocket.dto.response.RoomDtoResponse;
import com.example.websocket.entity.Room;
import com.example.websocket.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<RoomDtoResponse> getAllRooms() {

        List<Room> rooms = roomRepository.findAll();
        Collections.reverse(rooms);

        return rooms.stream()
                .map(RoomDtoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public String createRoom(String name) {
        Room room = new Room();
        room.setName(name);
        Room savedRoom = roomRepository.save(room);
        return savedRoom.getName();
    }

    public RoomDtoResponse getRoomDetail(Long roomId) {
        Room findRoom = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalStateException());
        return RoomDtoResponse.fromEntity(findRoom);
    }
}
