package com.ecom.ecommerce.notification.repository;

import com.ecom.ecommerce.notification.entity.Notification;
import com.ecom.ecommerce.notification.kafka.payment.PaymentConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
