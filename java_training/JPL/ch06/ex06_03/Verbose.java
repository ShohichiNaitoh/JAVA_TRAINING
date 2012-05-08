package ch06.ex06_03;

interface Verbose {

	enum MessageLevel {
		SILENT,
		TERSE,
		NORMAL,
		VERBOSE,
	}

	void setVerbosity(MessageLevel level);
	MessageLevel getVerbosity();

}
