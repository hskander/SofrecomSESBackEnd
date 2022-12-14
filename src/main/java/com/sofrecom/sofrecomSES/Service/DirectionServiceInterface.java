package com.sofrecom.sofrecomSES.Service;

import com.sofrecom.sofrecomSES.Model.Direction;

import java.util.List;

public interface DirectionServiceInterface {

    List<Direction> getAllDirections();

    Direction updateDirection(Direction direction);

    Direction getDirectionById(Long id);

    void deleteDirection(Long id);

    Direction addDirection(Direction direction,Long employeId);
}
