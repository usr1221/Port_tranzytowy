package com.port.transitPort.service;

import com.port.transitPort.model.Port;
import com.port.transitPort.model.Ship;
import com.port.transitPort.model.Wharf;
import com.port.transitPort.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShipService {

    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private WharfService wharfService;

    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }

    public Ship getShipById(Long id) {
        return shipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ship not found with ID: " + id));
    }

    public Ship createShip(Ship ship) {
        return shipRepository.save(ship);
    }

    public Ship updateShip(Long id, Ship updatedShip) {
        Ship ship = getShipById(id);
        ship.setName(updatedShip.getName());
        ship.setHomePort(updatedShip.getHomePort());
        ship.setDraft(updatedShip.getDraft());
        ship.setLength(updatedShip.getLength());
        ship.setCallSign(updatedShip.getCallSign());
        ship.setType(updatedShip.getType());
        if (updatedShip.getWharf() != null) {
            Wharf wharf = wharfService.getWharfById(updatedShip.getWharf().getId());
            ship.setWharf(wharf);
        } else {
            ship.setWharf(null); // Brak przypisania do nabrzeża
        }
        return shipRepository.save(ship);
    }

    public void deleteShip(Long id) {
        if (!shipRepository.existsById(id)) {
            throw new RuntimeException("Ship not found with ID: " + id);
        }
        shipRepository.deleteById(id);
    }
}
