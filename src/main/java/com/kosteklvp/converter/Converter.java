package com.kosteklvp.converter;

public interface Converter<F, T> {

  T convert(F from);

}
