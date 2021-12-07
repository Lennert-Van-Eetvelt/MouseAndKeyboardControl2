import java.io.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class SystemOutCatcher {
//    public String systemOut = Math.random() + "\n";
    public static String systemOut = "Hi, I can control mouseX, mouseY,click, keystrokes, delay & delayFlux \n" +
        "I run on port 7777\n\n\n";
    ConsoleOutputCapturer consoleOutputCapturer = new ConsoleOutputCapturer();


    boolean f = true;

    public String getSystemOut(){
        if (f)
            consoleOutputCapturer.start();
        f = false;
        systemOut += consoleOutputCapturer.stop();

        consoleOutputCapturer.start();

//        return "<html><font size='1.2'; backgroundColor = white;>" +systemOut.replace("\n", "<br>") + "</font></html>";
        return systemOut;
    }




    public class ConsoleOutputCapturer {
        private ByteArrayOutputStream baos;
        private PrintStream previous;
        private boolean capturing;

        public void start() {
            if (capturing) {
                return;
            }

            capturing = true;
            previous = System.out;
            baos = new ByteArrayOutputStream();

            OutputStream outputStreamCombiner =
                    new OutputStreamCombiner(Arrays.asList(previous, baos));
            PrintStream custom = new PrintStream(outputStreamCombiner);

            System.setOut(custom);
        }

        public String stop() {
            if (!capturing) {
                return "";
            }

            System.setOut(previous);

            String capturedValue = baos.toString();

            baos = null;
            previous = null;
            capturing = false;

            return capturedValue;
        }

        private class OutputStreamCombiner extends OutputStream {
            private List<OutputStream> outputStreams;

            public OutputStreamCombiner(List<OutputStream> outputStreams) {
                this.outputStreams = outputStreams;
            }

            public void write(int b) throws IOException {
                for (OutputStream os : outputStreams) {
                    os.write(b);
                }
            }

            public void flush() throws IOException {
                for (OutputStream os : outputStreams) {
                    os.flush();
                }
            }

            public void close() throws IOException {
                for (OutputStream os : outputStreams) {
                    os.close();
                }
            }
        }
    }
}
