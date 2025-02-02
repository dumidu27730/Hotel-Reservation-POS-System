package edu.icet.service.custom;

import edu.icet.dto.Guest;
import edu.icet.service.SuperService;
import javafx.collections.ObservableList;

public interface GuestService  extends SuperService{
    ObservableList<Guest> getAll();

    boolean addGuest(Guest guest);

    boolean updateGuest(Guest guest);

    boolean deleteGuest(String guestId);

    Guest searchGuest(String guestId);
}
