package com.paymentchain.billing.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "This model is used to return errors in RFC 7807 which created a generalized error-handling schema composed by five parts")
@NoArgsConstructor
@Data
public class StandarizedApiExceptionresponse {
	
	@Schema(description = "The unique uri iditifier that categorizes the error", name = "type",
			requiredMode = Schema.RequiredMode.REQUIRED, example = "/errors/authentication/not-authorized")
	private String type;
	
	@Schema(description = "The unique uri iditifier that categorizes the error", name = "title",
			requiredMode = Schema.RequiredMode.REQUIRED, example = "The user does not have authorization")
	private String title;
	
	@Schema(description = "The unique uri iditifier that categorizes the error", name = "code",
			requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "192")
	private String code;
	
	@Schema(description = "The unique uri iditifier that categorizes the error", name = "detail",
			requiredMode = Schema.RequiredMode.REQUIRED, example = "The user does not hace the propertly permissions to acces the "
					+ "resource, please contact with us https://stobotero.com")
	private String detail;
	
	@Schema(description = "The unique uri iditifier that categorizes the error", name = "instance",
			requiredMode = Schema.RequiredMode.REQUIRED, example = "/errors/authentication/not-authorized/01")
	private String instance;

	public StandarizedApiExceptionresponse(String type, String title, String code, String detail) {
		super();
		this.type = type;
		this.title = title;
		this.code = code;
		this.detail = detail;
	}

	
}
