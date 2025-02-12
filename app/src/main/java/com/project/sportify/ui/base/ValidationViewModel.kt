package com.project.sportify.ui.base

import android.util.Patterns
import com.project.sportify.data.models.error.ErrorType
import com.project.sportify.data.models.fieldtype.FieldType
import com.project.sportify.data.models.validationtype.ValidationType
import com.project.sportify.utils.meetsRequirements

abstract class ValidationViewModel<Event : ViewEvent, UiState : ViewState, Effect : ViewSideEffect> :
    BaseViewModel<Event, UiState, Effect>() {


    protected fun validateField(fieldType: FieldType, field: String?): ValidationType {
        return when (fieldType) {
            FieldType.Email -> validateEmail(field)
            FieldType.Password -> validatePassword(field)
            FieldType.FirstName -> validateName(fieldType, field)
            FieldType.LastName -> validateName(fieldType, field)
            FieldType.PhoneNumber -> validatePhoneNumber(field)
            FieldType.BirthOfDate -> validateBirthOfDate(field)
            FieldType.UserName, FieldType.Text -> validateUserName(field)
        }
    }

    private fun validateBirthOfDate(birthOfDate: String?): ValidationType {
        return if (birthOfDate.isNullOrEmpty()) {
            ValidationType(FieldType.BirthOfDate, ErrorType.EMPTY)
        } else {
            ValidationType(FieldType.BirthOfDate, ErrorType.NONE)
        }
    }

    private fun validateEmail(email: String?): ValidationType {
        return if (email.isNullOrEmpty()) {
            ValidationType(FieldType.Email, ErrorType.EMPTY)
        } else {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                ValidationType(FieldType.Email, ErrorType.NONE)
            } else {
                ValidationType(FieldType.Email, ErrorType.NOT_EMAIL)

            }
        }
    }

    private fun validateUserName(username: String?): ValidationType {
        return if (username.isNullOrEmpty()) {
            ValidationType(FieldType.UserName, ErrorType.EMPTY)
        } else {
            ValidationType(FieldType.UserName, ErrorType.NONE)
        }
    }

    private fun validatePhoneNumber(phoneNumber: String?): ValidationType {
        return if (phoneNumber.isNullOrEmpty()) {
            ValidationType(FieldType.PhoneNumber, ErrorType.EMPTY)
        } else {
            if (phoneNumber.length == 10 && Patterns.PHONE.matcher(phoneNumber).matches()) {
                ValidationType(FieldType.PhoneNumber, ErrorType.NONE)
            } else {
                ValidationType(FieldType.PhoneNumber, ErrorType.NOT_PHONE)
            }
        }
    }


    private fun validatePassword(password: String?): ValidationType {
        return if (!password.isNullOrEmpty()) {
            if(password.length < 8){
                ValidationType(FieldType.Password, ErrorType.LENGTH)
            }else {
                ValidationType(FieldType.Password, ErrorType.NONE)
            }
        } else {
            ValidationType(FieldType.Password, ErrorType.EMPTY)
        }
    }
    fun validateCreateNewPassword(password: String?): ValidationType {

        return if (!password.isNullOrEmpty()) {
            if (password.length < 8) {
                ValidationType(FieldType.Password, ErrorType.LENGTH)
            } else {
                if (password.meetsRequirements) {
                    ValidationType(FieldType.Password, ErrorType.NONE)
                } else {
                    ValidationType(FieldType.Password, ErrorType.NOT_PASSWORD)
                }
            }
        } else {
            ValidationType(FieldType.Password, ErrorType.EMPTY)
        }
    }
    fun validateConfirmNewPassword(password: String?, confirmPassword: String?): ValidationType {

        return if (!password.isNullOrEmpty()) {
            if (password.length < 8) {
                ValidationType(FieldType.Password, ErrorType.LENGTH)
            } else {
                if (password.meetsRequirements) {
                    if (password == confirmPassword) {
                        ValidationType(FieldType.Password, ErrorType.NONE)
                    } else {
                        ValidationType(FieldType.Password, ErrorType.NOT_MATCH)
                    }
                } else {
                    ValidationType(FieldType.Password, ErrorType.NOT_PASSWORD)
                }
            }
        } else {
            ValidationType(FieldType.Password, ErrorType.EMPTY)
        }
    }
    private fun validateName(fieldType: FieldType, name: String?): ValidationType {
        return if (!name.isNullOrEmpty()) {
            ValidationType(fieldType, ErrorType.NONE)
        } else {
            ValidationType(fieldType, ErrorType.EMPTY)
        }
    }

    public override fun onCleared() {
        super.onCleared()
    }

}
