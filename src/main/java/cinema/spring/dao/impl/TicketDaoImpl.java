package cinema.spring.dao.impl;

import cinema.spring.dao.AbstractDao;
import cinema.spring.dao.TicketDao;
import cinema.spring.model.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl extends AbstractDao<Ticket> implements TicketDao {
    public TicketDaoImpl(SessionFactory factory) {
        super(factory, Ticket.class);
    }
}
