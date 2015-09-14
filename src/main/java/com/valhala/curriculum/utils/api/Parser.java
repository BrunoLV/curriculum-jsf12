/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.utils.api;

import java.util.List;

/**
 * @param <T1>
 * @param <T2>
 * @author bruno
 */
public interface Parser<T1, T2> {

    T2 parse(T1 objeto);

    T1 inverseParse(T2 objeto);

    List<T2> massiveParse(List<T1> list);

    List<T1> massiveInverseParse(List<T2> list);

    T2 parseWithoutRelationship(T1 objeto);

    List<T2> massiveParseWithoutRelationship(List<T1> list);

}
