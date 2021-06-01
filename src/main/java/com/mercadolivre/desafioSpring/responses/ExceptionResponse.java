package com.mercadolivre.desafioSpring.responses;

public class ExceptionResponse {
    private Integer status;
    private String message;
    private Long timestemp;

    public ExceptionResponse(Integer status, String message, Long timestemp) {
        this.status = status;
        this.message = message;
        this.timestemp = timestemp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestemp() {
        return timestemp;
    }

    public void setTimestemp(Long timestemp) {
        this.timestemp = timestemp;
    }
}
