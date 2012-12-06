/**
 * 
 */
package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility thread class which consumes and displays stream input.
 * 
 * Original code taken from http://www.javaworld.com/javaworld/jw-12-2000/jw-1229-traps.html?page=4
 */
class StreamGobbler
    extends Thread
{
    static private Log log = LogFactory.getLog(StreamGobbler.class);
    private InputStream inputStream;
    private String streamType;
    private boolean displayStreamOutput;

    /**
     * Constructor.
     * 
     * @param inputStream the InputStream to be consumed
     * @param streamType the stream type (should be OUTPUT or ERROR)
     * @param displayStreamOutput whether or not to display the output of the stream being consumed
     */
    StreamGobbler(final InputStream inputStream,
                  final String streamType,
                  final boolean displayStreamOutput)
    {
        this.inputStream = inputStream;
        this.streamType = streamType;
        this.displayStreamOutput = displayStreamOutput;
    }

    /**
     * Consumes the output from the input stream and displays the lines consumed if configured to do so.
     */
    @Override
    public void run()
    {
        try
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                if (displayStreamOutput)
                {
                    System.out.println(streamType + ">" + line);
                }
            }
        }
        catch (IOException ex)
        {
            log.error("Failed to successfully consume and display the input stream of type " + streamType + ".", ex);
            ex.printStackTrace();
        }
    }
}