/**********************************************************************
 * Copyright (c) 2002-2004 IBM Canada and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v0.5
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v05.html
 * 
 * Contributors: 
 * IBM Rational Software - Initial API and implementation */
package org.eclipse.cdt.internal.core.parser2.c;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.IASTArrayDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTArrayModifier;

/**
 * @author jcamelon
 */
public class CASTArrayDeclarator extends CASTDeclarator implements
        IASTArrayDeclarator {

    private int currentIndex = 0;
    private void removeNullArrayModifiers() {
        int nullCount = 0; 
        for( int i = 0; i < arrayMods.length; ++i )
            if( arrayMods[i] == null )
                ++nullCount;
        if( nullCount == 0 ) return;
        IASTArrayModifier [] old = arrayMods;
        int newSize = old.length - nullCount;
        arrayMods = new IASTArrayModifier[ newSize ];
        for( int i = 0; i < newSize; ++i )
            arrayMods[i] = old[i];
        currentIndex = newSize;
    }

    
    private IASTArrayModifier [] arrayMods = null;
    private static final int DEFAULT_ARRAYMODS_LIST_SIZE = 4;


    public List getArrayModifiers() {
        if( arrayMods == null ) return Collections.EMPTY_LIST;
        removeNullArrayModifiers();
        return Arrays.asList( arrayMods );
 
    }

    public void addArrayModifier(IASTArrayModifier arrayModifier) {
        if( arrayMods == null )
        {
            arrayMods = new IASTArrayModifier[ DEFAULT_ARRAYMODS_LIST_SIZE ];
            currentIndex = 0;
        }
        if( arrayMods.length == currentIndex )
        {
            IASTArrayModifier [] old = arrayMods;
            arrayMods = new IASTArrayModifier[ old.length * 2 ];
            for( int i = 0; i < old.length; ++i )
                arrayMods[i] = old[i];
        }
        arrayMods[ currentIndex++ ] = arrayModifier;
    }

}
