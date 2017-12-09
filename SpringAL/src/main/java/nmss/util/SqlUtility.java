package nmss.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("sqlUtility")
public class SqlUtility {

	private String dbType;

	@Autowired
	public SqlUtility(@Value("${db.connection.type}") String dbType) throws Exception {
		if (dbType == null || (!"mysql".equalsIgnoreCase(dbType) && !"orcl".equalsIgnoreCase(dbType)))
			throw new Exception("Database can be only Mysql or Orcl");
		this.dbType = dbType;
	}

	public String getDbType() {
		return dbType;
	}

	public String toChar(String colname, String dateformat) {
		String res = null;

		if ("mysql".equalsIgnoreCase(dbType)) {
			res = "DATE_FORMAT(" + colname + ",'" + makeFormat(dateformat) + "')";
		} else {
			dateformat = dateformat.toUpperCase();
			res = "TO_CHAR(" + colname + ",'" + makeFormat(dateformat) + "')";
		}
		return res;
	}

	public String toDate(String date, String javaDateFormat) {
		String res = null;

		String format = makeFormat(javaDateFormat);
		if ("mysql".equalsIgnoreCase(dbType)) {
			res = "STR_TO_DATE('" + date + "','" + format + "')";
		} else {
			res = "TO_DATE('" + date + "','" + format + "')";
		}

		return res;
	}

	public String toDate(String date) {
		return toDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public String toChar(String colname) {
		return toChar(colname, "yyyy-MM-dd HH:mm:ss");
	}

	public String currentDate() {
		String res = null;
		if (dbType != null && "mysql".equalsIgnoreCase(dbType)) {
			res = "now()";
		} else {
			res = "sysdate";
		}
		return res;
	}

	public String nvl(String colname, String defaultval) {
		String res = null;

		if (dbType != null && "mysql".equalsIgnoreCase(dbType)) {
			res = "IFNULL(" + colname + "," + defaultval + ")";
		} else {
			res = "nvl(" + colname + "," + defaultval + ")";
		}
		return res;
	}

	public String to_number(String val) {
		String res = null;
		if ("mysql".equalsIgnoreCase(dbType)) {
			res = "CAST(" + val + " AS UNSIGNED)";
		} else {
			res = "to_number(" + val + ")";
		}
		return res;
	}

	public String decode(String colname, String conditionval, String val1, String val2) {
		String res = null;
		if ("mysql".equalsIgnoreCase(dbType)) {
			res = "DECODE(" + colname + "," + conditionval + "," + val1 + "," + val2 + ")";
		} else {
			res = "if(" + colname + "=" + conditionval + "," + val1 + "," + val2 + ")";
		}
		return res;

	}

	private String makeFormat(String format) {
		String val = null;
		switch (dbType.toLowerCase()) {
		case "mysql":
			val = format.replace("yyyy", "%Y"); // Year 2017
			val = val.replace("MMMMM", "%M"); // Month complete name (June)
			val = val.replace("MMM", "%b"); // Abbrevation Month name (Jun ,
											// Oct)
			val = val.replace("MM", "%m"); // month
			val = val.replace("yy", "%y"); // Year 17
			val = val.replace("dd", "%d"); // date
			val = val.replace("HH", "%H"); // Hours in 24 format
			val = val.replace("hh", "%h"); // Hours in 12 format
			val = val.replace("mm", "%i"); // Minutes
			val = val.replace("ss", "%s"); // Seconds
			val = val.replace("EEEEE", "%W"); // Weedays Complete Name (Monday)
			val = val.replace("EEE", "%a"); // Abbrevation Weedays Name (Mon)
			val = val.replace("F", "%w"); // Day of week (Sunday=1 oracle)
											// (Sunday=0 mysql , java)
			break;

		case "orcl":
			val = format.replace("HH", "HH24"); // Hours in 24 format
			val = val.replace("hh", "HH12"); // Hours in 12 format
			val = val.replace("mm", "mi"); // Minutes
			val = val.replace("MMMMM", "Month"); // Month complete name (June)
			val = val.replace("MMM", "Mon"); // Abbrevation Month name (Jun ,
												// Oct)
			val = val.replace("EEEEE", "Day"); // Weedays Complete Name (Monday)
			val = val.replace("EEE", "Dy"); // Abbrevation Weedays Name (Mon)
			val = val.replace("F", "D"); // Day of week (Sunday=1 oracle)
											// (Sunday=0 mysql , java)
			break;
		default:
			val = null;
		}
		return val;
	}
}
