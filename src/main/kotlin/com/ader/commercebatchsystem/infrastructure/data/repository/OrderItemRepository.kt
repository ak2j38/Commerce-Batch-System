package com.ader.commercebatchsystem.infrastructure.data.repository

import com.ader.commercebatchsystem.domain.entity.order.OrderItem
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemRepository: JpaRepository<OrderItem, Long>
