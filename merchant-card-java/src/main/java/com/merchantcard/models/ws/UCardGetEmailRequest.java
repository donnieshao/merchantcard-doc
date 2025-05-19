package com.merchantcard.models.ws;

import com.merchantcard.models.APApiBaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UCardGetEmailRequest extends APApiBaseRequest {
    /**
     * 卡片id
     */
    private Integer userBankcardId;
}
