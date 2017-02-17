package filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;

public class WrappedOutputStream extends ServletOutputStream {
	private ByteArrayOutputStream buffer;

	public WrappedOutputStream(ByteArrayOutputStream buffer) {
		this.buffer = buffer;
	}

	public void write(int b) throws IOException {
		buffer.write(b);
	}

	public byte[] toByteArray() {
		return buffer.toByteArray();
	}

}
