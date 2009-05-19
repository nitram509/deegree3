//$HeadURL$
/*----------------    FILE HEADER  ------------------------------------------
 This file is part of deegree.
 Copyright (C) 2001-2009 by:
 Department of Geography, University of Bonn
 http://www.giub.uni-bonn.de/deegree/
 lat/lon GmbH
 http://www.lat-lon.de

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.
 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 Lesser General Public License for more details.
 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 Contact:

 Andreas Poth
 lat/lon GmbH
 Aennchenstr. 19
 53177 Bonn
 Germany
 E-Mail: poth@lat-lon.de

 Prof. Dr. Klaus Greve
 Department of Geography
 University of Bonn
 Meckenheimer Allee 166
 53115 Bonn
 Germany
 E-Mail: greve@giub.uni-bonn.de
 ---------------------------------------------------------------------------*/

package org.deegree.rendering.r3d.model.geometry;

import java.io.IOException;
import java.io.Serializable;

import org.deegree.commons.utils.memory.AllocatedHeapMemory;
import org.deegree.commons.utils.memory.MemoryAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The <code>SimpleGeometryStyle</code> class TODO add class documentation here.
 * 
 * @author <a href="mailto:bezema@lat-lon.de">Rutger Bezema</a>
 * @author last edited by: $Author$
 * @version $Revision$, $Date$
 * 
 */
public class SimpleGeometryStyle implements MemoryAware, Serializable {

    private final static Logger LOG = LoggerFactory.getLogger( SimpleGeometryStyle.class );

    /**
     * 
     */
    private static final long serialVersionUID = -5069487647474073270L;

    private transient int specularColor;

    private transient int ambientColor;

    private transient int diffuseColor;

    private transient int emmisiveColor;

    private transient float shininess;

    /**
     * set the colors to 0x090909FF, emmisive to 0 and the shininess to 20;
     */
    public SimpleGeometryStyle() {
        this.specularColor = 0x040404FF;
        this.ambientColor = 0x090909FF;
        this.diffuseColor = 0x090909FF;
        this.emmisiveColor = 0;
        this.shininess = 20;
    }

    /**
     * @param specularColor
     * @param ambientColor
     * @param diffuseColor
     * @param emmisiveColor
     * @param shininess
     */
    public SimpleGeometryStyle( int specularColor, int ambientColor, int diffuseColor, int emmisiveColor,
                                float shininess ) {
        this.specularColor = specularColor;
        this.ambientColor = ambientColor;
        this.diffuseColor = diffuseColor;
        this.emmisiveColor = emmisiveColor;
        this.shininess = shininess;
    }

    /**
     * @return the specularColor
     */
    public final int getSpecularColor() {
        return specularColor;
    }

    /**
     * @param specularColor
     *            the specularColor to set
     */
    public final void setSpecularColor( int specularColor ) {
        this.specularColor = specularColor;
    }

    /**
     * @return the ambientColor
     */
    public final int getAmbientColor() {
        return ambientColor;
    }

    /**
     * @param ambientColor
     *            the ambientColor to set
     */
    public final void setAmbientColor( int ambientColor ) {
        this.ambientColor = ambientColor;
    }

    /**
     * @return the diffuseColor
     */
    public final int getDiffuseColor() {
        return diffuseColor;
    }

    /**
     * @param diffuseColor
     *            the diffuseColor to set
     */
    public final void setDiffuseColor( int diffuseColor ) {
        this.diffuseColor = diffuseColor;
    }

    /**
     * @return the emmisiveColor
     */
    public final int getEmmisiveColor() {
        return emmisiveColor;
    }

    /**
     * @param emmisiveColor
     *            the emmisiveColor to set
     */
    public final void setEmmisiveColor( int emmisiveColor ) {
        this.emmisiveColor = emmisiveColor;
    }

    /**
     * @return the shininess
     */
    public final float getShininess() {
        return shininess;
    }

    /**
     * @param shininess
     *            the shininess to set
     */
    public final void setShininess( float shininess ) {
        this.shininess = shininess;
    }

    /**
     * Method called while serializing this object
     * 
     * @param out
     *            to write to.
     * @throws IOException
     */
    private void writeObject( java.io.ObjectOutputStream out )
                            throws IOException {
        LOG.trace( "Serializing to object stream" );

        out.writeInt( specularColor );
        out.writeInt( ambientColor );
        out.writeInt( diffuseColor );
        out.writeInt( emmisiveColor );
        out.writeFloat( shininess );

    }

    /**
     * Method called while de-serializing (instancing) this object.
     * 
     * @param in
     *            to create the methods from.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject( java.io.ObjectInputStream in )
                            throws IOException {
        LOG.trace( "Deserializing from object stream" );
        // length 32 bit only lowest 24 are used rgb
        specularColor = in.readInt();
        // length 32 bit only lowest 24 are used rgb
        ambientColor = in.readInt();
        // length 32 bit only lowest 24 are used rgb
        diffuseColor = in.readInt();
        // length 32 bit only lowest 24 are used rgb
        emmisiveColor = in.readInt();
        // a single value
        shininess = in.readFloat();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "\nspecularColor: " ).append( specularColor );
        sb.append( "\nambientColor: " ).append( ambientColor );
        sb.append( "\ndiffuseColor: " ).append( diffuseColor );
        sb.append( "\nemmisiveColor: " ).append( emmisiveColor );
        sb.append( "\nshininess: " ).append( shininess );
        return sb.toString();
    }

    /**
     * @return the bytes this style occupies
     */
    public long sizeOf() {
        long localSize = AllocatedHeapMemory.INSTANCE_SIZE;
        // abmient, diffuse, emmisive, specular, vertexCount
        localSize += ( 5 * AllocatedHeapMemory.INT_SIZE );
        // shininess
        localSize += AllocatedHeapMemory.FLOAT_SIZE;
        return localSize;
    }

}