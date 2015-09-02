/*******************************************************************************
 * Copyright (c) 2015 MEDEVIT <office@medevit.at>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     MEDEVIT <office@medevit.at> - initial API and implementation
 ******************************************************************************/
package eb.core.shared;

import java.io.OutputStream;
import java.io.Serializable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.xml.sax.InputSource;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "rdbmsType", "hostName", "port", "databaseName", "connectionString", "username", "password",
		"text" })
public class DBConnection implements Serializable {

	private static final long serialVersionUID = -7571011690246990109L;

	@XmlType
	@XmlEnum(String.class)
	public enum DBType {
		@XmlEnumValue("MYSQL") MySQL("com.mysql.jdbc.Driver", "mySQl", "3306"), @XmlEnumValue("PostgreSQL") PostgreSQL(
				"org.postgresql.Driver", "PostgreSQL", "5432"), @XmlEnumValue("H2") H2("org.h2.Driver", "H2", "");

		public final String driverName;
		public final String dbType;
		public final String defaultPort;

		DBType(String driverName, String dbType, String defaultPort) {
			this.driverName = driverName;
			this.dbType = dbType;
			this.defaultPort = defaultPort;
		}
	}

	public DBType rdbmsType;
	@XmlAttribute
	public String hostName;
	@XmlAttribute
	public String port;
	@XmlAttribute
	public String databaseName;
	@XmlAttribute
	public String connectionString;
	@XmlAttribute
	public String username;
	@XmlAttribute
	public String password;
	@XmlAttribute
	public String text;

	/**
	 * are all required values for the DBConnection set?
	 * 
	 * @return
	 */
	public boolean allValuesSet() {
		boolean result = true;

		result = (rdbmsType != null);

		if (!DBType.H2.equals(rdbmsType)) {
			result = (hostName != null);
		}

		result = (databaseName != null);
		result = (username != null);

		return result;
	}

	/**
	 * Marshall this object into a storable xml
	 * 
	 * @param os
	 * @throws JAXBException
	 */
	public void marshall(OutputStream os) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(DBConnection.class);
		Marshaller m = jaxbContext.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(this, os);
	}

	/**
	 * Unmarshall a DBConnection object created by {@link #marshall()}
	 * 
	 * @param is
	 * @return
	 * @throws JAXBException
	 */
	public static DBConnection unmarshall(InputSource is) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(DBConnection.class);
		Unmarshaller um = jaxbContext.createUnmarshaller();
		Object o = um.unmarshal(is);
		return (DBConnection) o;
	}
}
