package unionissue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;

import unionissue.avrogenerated.SecondaryDispatchDateTimeTypeEnum;
import unionissue.avrogenerated.SecondaryDispatchDateTimeValue;

public class AvroSecondaryDispatchDateTimeValueTest {

  @Test
  public void testWithInstantValue() throws Exception {
    final Instant value = Instant.now();
    final SecondaryDispatchDateTimeValue valueToMarshal
        = SecondaryDispatchDateTimeValue.newBuilder().setDispatchType(SecondaryDispatchDateTimeTypeEnum.FST)
            .setDispatchValue(value).setDispatchChangeIndicator(false).build();
    marshalThenUnmarshal(valueToMarshal);
  }

  @Test
  public void testWithNullValue() throws Exception {
    final Instant value = null;
    final SecondaryDispatchDateTimeValue valueToMarshal
        = SecondaryDispatchDateTimeValue.newBuilder().setDispatchType(SecondaryDispatchDateTimeTypeEnum.FST)
            .setDispatchValue(value).setDispatchChangeIndicator(false).build();
    marshalThenUnmarshal(valueToMarshal);
  }

  private void marshalThenUnmarshal(SecondaryDispatchDateTimeValue valueToMarshal) throws IOException {
    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    final byte[] bytes;
    try (final DataFileWriter<SecondaryDispatchDateTimeValue> dataFileWriter
        = new DataFileWriter<>(new SpecificDatumWriter<>(SecondaryDispatchDateTimeValue.class))) {

      dataFileWriter.create(valueToMarshal.getSchema(), baos);
      dataFileWriter.append(valueToMarshal);
    }
    bytes = baos.toByteArray();

    try (SeekableByteArrayInput bais = new SeekableByteArrayInput(bytes);
        final DataFileReader<SecondaryDispatchDateTimeValue> dataFileReader
            = new DataFileReader<>(bais, new SpecificDatumReader<>(SecondaryDispatchDateTimeValue.class))) {
      SecondaryDispatchDateTimeValue unmarshaledValue = dataFileReader.next();
      System.out.println(unmarshaledValue);
    }
  }

}
