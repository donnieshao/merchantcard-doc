package com.merchantcard.sunrate;

import com.merchantcard.models.APApiBaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SRCardSaveEmailRequest extends APApiBaseRequest {
    /**
     * 卡片id
     */
    private Integer userBankcardId;
    /**
     * 邮箱 邮箱
     */
    private String email;
}
