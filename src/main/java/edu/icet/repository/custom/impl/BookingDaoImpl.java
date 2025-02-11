package edu.icet.repository.custom.impl;

import edu.icet.entity.BookingEntity;
import edu.icet.repository.custom.BookingDao;
import edu.icet.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements BookingDao {
    @Override
    public List<String> getIds() {
        return List.of();
    }

    @Override
    public boolean save(BookingEntity entity) {
        try {
            String SQL = "INSERT INTO bookings(user_id,guest_id,room_number,check_in_date,check_in_time,days,check_out_date,guest_count,total_amount,reservation_status) VALUES (?,?,?,?,?,?,?,?,?,?)";

            return CrudUtil.execute(SQL,
                    entity.getUserId(),
                    entity.getGuestId(),
                    entity.getRoomNumber(),
                    entity.getCheckInDate(),
                    entity.getCheckInTime(),
                    entity.getDays(),
                    entity.getCheckInOut(),
                    entity.getGuestCount(),
                    entity.getTotalAmount(),
                    entity.getReservationStatus()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookingEntity search(String s) {
        String SQL = "SELECT * FROM Bookings WHERE booking_id=? ";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL,s);

            if(resultSet.next()) {
                return new BookingEntity(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getDate(5).toLocalDate(),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getDate(8).toLocalDate(),
                        resultSet.getInt(9),
                        resultSet.getFloat(10),
                        resultSet.getString(11)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean delete(String s) {
        String SQL = "DELETE FROM bookings WHERE booking_id= ?";
        try {
            return CrudUtil.execute(SQL,s);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(BookingEntity entity) {
        String SQL = "UPDATE sbooking SET  user_id=? , guest_id=?,room_number =? ,check_in_date= ?,check_in_time=?,days=?,check_out_date=?,guest_count=?,total_amount=?reservation_status=? WHERE booking_id=? ";
        try {
            return CrudUtil.execute(SQL,
                    entity.getUserId(),
                    entity.getGuestId(),
                    entity.getRoomNumber(),
                    entity.getCheckInDate(),
                    entity.getCheckInTime(),
                    entity.getDays(),
                    entity.getCheckInOut(),
                    entity.getGuestCount(),
                    entity.getTotalAmount(),
                    entity.getReservationStatus(),
                    entity.getBookingId()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BookingEntity> getAll() {
        List<BookingEntity> bookingList = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM bookings");
            while (resultSet.next()) {
                bookingList.add(new BookingEntity(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getDate(5).toLocalDate(),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getDate(8).toLocalDate(),
                        resultSet.getInt(9),
                        resultSet.getFloat(10),
                        resultSet.getString(11)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookingList;
    }
}
