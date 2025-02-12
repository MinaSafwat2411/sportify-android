package com.project.sportify.data.models.validationtype

import androidx.annotation.Keep
import com.project.sportify.data.models.error.ErrorType
import com.project.sportify.data.models.fieldtype.FieldType

@Keep
data class ValidationType(val fieldType: FieldType, val errorType: ErrorType)