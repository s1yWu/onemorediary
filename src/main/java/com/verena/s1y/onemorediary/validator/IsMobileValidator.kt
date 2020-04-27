package com.verena.s1y.onemorediary.validator

import com.verena.s1y.onemorediary.util.StringUtil
import org.springframework.util.StringUtils
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class IsMobileValidator : ConstraintValidator<IsMobile, String> {

    private var required : Boolean ?= null

    override fun isValid(p0: String?, p1: ConstraintValidatorContext?) = when {
            required!! -> StringUtil.isMobile(p0)
            StringUtils.isEmpty(p0) -> true
            else -> StringUtil.isMobile(p0)
    }

    override fun initialize(constraintAnnotation: IsMobile?) {
        super.initialize(constraintAnnotation)
        required = constraintAnnotation!!.required
    }
}