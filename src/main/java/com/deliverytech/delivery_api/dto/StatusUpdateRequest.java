package com.deliverytech.delivery_api.dto;

import lombok.Data;
import com.deliverytech.delivery_api.entity.StatusOrder;

@Data
public class StatusUpdateRequest {
    private StatusOrder status;

}
