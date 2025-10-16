package org.reapirflow.repairflow.Pojo.TicketPojo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.reapirflow.repairflow.Pojo.UserPojo.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "tickets")
public class Ticket {

    public enum Status {
        PENDING, ASSIGNED, QUOTED, AWAITING_DEVICE,
        DEVICE_RECEIVED, IN_PROGRESS, READY_FOR_CONFIRMATION,
        PAID, SHIPPED, DELIVERED
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false, length = 120)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    // User Association
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private User employee;

    //ticket Status
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private Status status = Status.PENDING;

    @Column(name = "quote_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal quoteAmount = BigDecimal.ZERO;

    @Column(name = "paid", nullable = false)
    private boolean paid = false;

    //Time stamp
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", quoteAmount=" + quoteAmount +
                ", paid=" + paid +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
