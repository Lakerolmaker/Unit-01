package programs_console;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPHTTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.io.CopyStreamEvent;
import org.apache.commons.net.io.CopyStreamListener;
import org.apache.commons.net.util.TrustManagerUtils;

import LakerLibrary.console;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public final class FTPProgram
{

    public static final String USAGE =
        "Expected Parameters: [options] <hostname> <username> <password> [<remote file> [<local file>]]\n" +
        "\nDefault behavior is to download a file and use ASCII transfer mode.\n" +
        "\t-a - use local active mode (default is local passive)\n" +
        "\t-A - anonymous login (omit username and password parameters)\n" +
        "\t-b - use binary transfer mode\n" +
        "\t-c cmd - issue arbitrary command (remote is used as a parameter if provided) \n" +
        "\t-d - list directory details using MLSD (remote is used as the pathname if provided)\n" +
        "\t-e - use EPSV with IPv4 (default false)\n" +
        "\t-E - encoding to use for control channel\n" +
        "\t-f - issue FEAT command (remote and local files are ignored)\n" +
        "\t-h - list hidden files (applies to -l and -n only)\n" +
        "\t-k secs - use keep-alive timer (setControlKeepAliveTimeout)\n" +
        "\t-l - list files using LIST (remote is used as the pathname if provided)\n" +
        "\t     Files are listed twice: first in raw mode, then as the formatted parsed data.\n" +
        "\t     N.B. if the wrong server-type is used, output may be lost. Use -U or -S as necessary.\n" +
        "\t-L - use lenient future dates (server dates may be up to 1 day into future)\n" +
        "\t-m - list file details using MDTM (remote is used as the pathname if provided)\n" +
        "\t-n - list file names using NLST (remote is used as the pathname if provided)\n" +
        "\t-p true|false|protocol[,true|false] - use FTPSClient with the specified protocol and/or isImplicit setting\n" +
        "\t-s - store file on server (upload)\n" +
        "\t-S - systemType set server system type (e.g. UNIX VMS WINDOWS)\n" +
        "\t-t - list file details using MLST (remote is used as the pathname if provided)\n" +
        "\t-U - save unparseable responses\n" +
        "\t-w msec - wait time for keep-alive reply (setControlKeepAliveReplyTimeout)\n" +
        "\t-T  all|valid|none - use one of the built-in TrustManager implementations (none = JVM default)\n" +
        "\t-y format - set default date format string\n" +
        "\t-Y format - set recent date format string\n" +
        "\t-Z timezone - set the server timezone for parsing LIST responses\n" +
        "\t-z timezone - set the timezone for displaying MDTM, LIST, MLSD, MLST responses\n" +
        "\t-PrH server[:port] - HTTP Proxy host and optional port[80] \n" +
        "\t-PrU user - HTTP Proxy server username\n" +
        "\t-PrP password - HTTP Proxy server password\n" +
        "\t-# - add hash display during transfers\n";

    private static FTPClient ftp;
    
    public static void start() {
    	
    		turnOn();
    	
    		console.external.print("");
    		console.external.print("FTP Console mode V 1.0");
    		console.external.printLine();
    		console.external.print("For commands type help");
    		console.external.saveText();
    		
    		console.external.addKeydownEvent( "consoleFTPMain", KeyCode.ENTER , ()-> {
    					String input = "";
					input = console.external.getInput();
				
					switch (getFirstArg(input)) {
					
					case "ls":
						listFiles(true);
						break;
					case "dir":
						listFiles(false);
						break;
					case "connect":
						connect(input);
						break;
					case "login":
						login(input);
						break;
					case "cd":
						changeDIr(input);
						break;
					
					default:
						if(input.equals("") == false) {
							console.external.print("Command not found : '" + input + "'");
						}
						break;
					}//: end switch
					
					printCursor();
    		});
    		
    		
    }
    
	private static void changeDIr(String input){
		String[] args = getArgs(input);
		String[] content = getContent(args);
		
		try {
			print(ftp.listHelp());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void login(String input) {
		String[] args = getArgs(input);
		String[] content = getContent(args);
	
		
		try {
			String username = content[0];
			String password = content[1];
			
			 if (!ftp.login(username, password))
	         {
	             ftp.logout();
	             error("Could not login");
	         }else {
	        	 	print("Logged in");
	        		print("Remote system is " + ftp.getSystemType());
	        	 	print(ftp.getReplyString());
	         }

	         
		} catch (Exception e) {
			error("Could no use the arguments specified");
			console.logg(e + " at " + e.getStackTrace()[0].getLineNumber());
		}
	
	}

	private static void connect(String input) {
		String[] args = getArgs(input);
		String[] content = getContent(args);
		
		try {
			String serverAdress = content[0];
			int port = Integer.valueOf(content[1]);
		
			ftp = new FTPClient();
	        try
	        {
	            int reply;
	            if (port > 0) {
	                ftp.connect(serverAdress, port);
	            } else {
	                ftp.connect(serverAdress);
	            }
	            print("Connected to " + serverAdress + " on port " + (port>0 ? port : ftp.getDefaultPort()));

	            // After connection attempt, you should check the reply code to verify
	            // success.
	            reply = ftp.getReplyCode();

	            if (!FTPReply.isPositiveCompletion(reply))
	            {
	                ftp.disconnect();
	                error("FTP server refused connection.");
	            }
	        }
	        catch (IOException e)
	        {
	            if (ftp.isConnected())
	            {
	                try
	                {
	                    ftp.disconnect();
	                }
	                catch (IOException f)
	                {
	                    // do nothing
	                }
	            }
	            error("Could not connect to server.");
	        
	        }
	
		} catch (Exception e) {
			error("Could no use the arguments specified");
		}
		
	}

	private static void listFiles(boolean showFiles) {

        try {
			for (FTPFile file : ftp.listFiles()) {
				if(file.isDirectory()) {
					String printed = "/";
					printed += file.getName();
				    print(printed);
				}else if((file.isFile()) && (showFiles) ) {
					String printed = "";
					printed += file.getName();
					printed += "\t Size :" + file.getSize();
				    print(printed);
				} 
			}
		} catch (Exception e) {
			error("Could not list files");
		}
		
	}

	private static void printCursor() {
		console.external.print(">");
		console.external.saveText();
	}
	
	private static void print(String input) {
		console.external.print(input);
	}
	
	private static void error(String input) {
		console.external.error(input);
	}
	
	private static void turnOff() {
		console.external.removeKeydownEvent("preventBackSpace");
		console.external.removeKeydownEvent("consoleFTPMain");
	}

	private static void turnOn() {

	}
	
	private static String getFirstArg(String text) {
		  if (text.indexOf(' ') > -1) { // Check if there is more than one word.
		      return text.substring(0, text.indexOf(' ')); // Extract first word.
		  } else {
		      return text; // Text is the first word itself.
		  }
	}
	
	private static String[] getArgs(String input) {
		return input.split(" ");
	}
	
	private static boolean contains(String[] input , String search) {
		for(String index : input) {
			if (index.equals(search)) 
				return true;
		}
		return false;
	}
	
	private static boolean hasArgs(String[] input) {
		for(String index : input) {
			if (index.charAt(0) == '-') 
				return true;
		}
		return false;
	}
	
	private static String[] getContent(String[] input) {
		ArrayList<String> temp =  new ArrayList<String>();
		for(int i = 0; i < input.length; i++) {
			if ((input[i].charAt(0) != '-') && (i != 0)) 
				temp.add(input[i]);
		}
		return temp.toArray(new String[0]);
	}

	private static String getContentAsOne(String[] input) {
		String temp = "";
		for(int i = 0; i < input.length; i++) {
			if ((input[i].charAt(0) != '-') && (i != 0)) 
				if(i == input.length - 1) {
					temp += input[i];
				}else {
					temp += input[i] + " ";
				}
				
		}
		return temp;
	}
	
    public static void start2(String[] args) throws UnknownHostException
    {
        boolean storeFile = false, binaryTransfer = false, error = false, listFiles = false, listNames = false, hidden = false;
        boolean localActive = false, useEpsvWithIPv4 = false, feat = false, printHash = false;
        boolean mlst = false, mlsd = false, mdtm = false, saveUnparseable = false;
        boolean lenient = false;
        long keepAliveTimeout = -1;
        int controlKeepAliveReplyTimeout = -1;
        int minParams = 5; // listings require 3 params
        String protocol = null; // SSL protocol
        String doCommand = null;
        String trustmgr = null;
        String proxyHost = null;
        int proxyPort = 80;
        String proxyUser = null;
        String proxyPassword = null;
        String username = null;
        String password = null;
        String encoding = null;
        String serverTimeZoneId = null;
        String displayTimeZoneId = null;
        String serverType = null;
        String defaultDateFormat = null;
        String recentDateFormat = null;


        int base = 0;
        for (base = 0; base < args.length; base++)
        {
            if (args[base].equals("-s")) {
                storeFile = true;
            }
            else if (args[base].equals("-a")) {
                localActive = true;
            }
            else if (args[base].equals("-A")) {
                username = "anonymous";
                password = System.getProperty("user.name")+"@"+InetAddress.getLocalHost().getHostName();
            }
            else if (args[base].equals("-b")) {
                binaryTransfer = true;
            }
            else if (args[base].equals("-c")) {
                doCommand = args[++base];
                minParams = 3;
            }
            else if (args[base].equals("-d")) {
                mlsd = true;
                minParams = 3;
            }
            else if (args[base].equals("-e")) {
                useEpsvWithIPv4 = true;
            }
            else if (args[base].equals("-E")) {
                encoding = args[++base];
            }
            else if (args[base].equals("-f")) {
                feat = true;
                minParams = 3;
            }
            else if (args[base].equals("-h")) {
                hidden = true;
            }
            else if (args[base].equals("-k")) {
                keepAliveTimeout = Long.parseLong(args[++base]);
            }
            else if (args[base].equals("-l")) {
                listFiles = true;
                minParams = 3;
            }
            else if (args[base].equals("-m")) {
                mdtm = true;
                minParams = 3;
            }
            else if (args[base].equals("-L")) {
                lenient = true;
            }
            else if (args[base].equals("-n")) {
                listNames = true;
                minParams = 3;
            }
            else if (args[base].equals("-p")) {
                protocol = args[++base];
            }
            else if (args[base].equals("-S")) {
                serverType = args[++base];
            }
            else if (args[base].equals("-t")) {
                mlst = true;
                minParams = 3;
            }
            else if (args[base].equals("-U")) {
                saveUnparseable = true;
            }
            else if (args[base].equals("-w")) {
                controlKeepAliveReplyTimeout = Integer.parseInt(args[++base]);
            }
            else if (args[base].equals("-T")) {
                trustmgr = args[++base];
            }
            else if (args[base].equals("-y")) {
                defaultDateFormat = args[++base];
            }
            else if (args[base].equals("-Y")) {
                recentDateFormat = args[++base];
            }
            else if (args[base].equals("-Z")) {
                serverTimeZoneId = args[++base];
            }
            else if (args[base].equals("-z")) {
                displayTimeZoneId = args[++base];
            }
            else if (args[base].equals("-PrH")) {
                proxyHost = args[++base];
                String parts[] = proxyHost.split(":");
                if (parts.length == 2){
                    proxyHost=parts[0];
                    proxyPort=Integer.parseInt(parts[1]);
                }
            }
            else if (args[base].equals("-PrU")) {
                proxyUser = args[++base];
            }
            else if (args[base].equals("-PrP")) {
                proxyPassword = args[++base];
            }
            else if (args[base].equals("-#")) {
                printHash = true;
            }
            else {
                break;
            }
        }

        int remain = args.length - base;
        if (username != null) {
            minParams -= 2;
        }
        if (remain < minParams) // server, user, pass, remote, local [protocol]
        {
            if (args.length > 0) {
                console.external.print("Actual Parameters: " + Arrays.toString(args));
            }
            console.external.print(USAGE);
            System.exit(1);
        }

        String server = args[base++];
        int port = 0;
        String parts[] = server.split(":");
        if (parts.length == 2){
            server=parts[0];
            port=Integer.parseInt(parts[1]);
        }
        if (username == null) {
            username = args[base++];
            password = args[base++];
        }

        String remote = null;
        if (args.length - base > 0) {
            remote = args[base++];
        }

        String local = null;
        if (args.length - base > 0) {
            local = args[base++];
        }

        final FTPClient ftp;
        if (protocol == null ) {
            if(proxyHost !=null) {
                console.external.print("Using HTTP proxy server: " + proxyHost);
                ftp = new FTPHTTPClient(proxyHost, proxyPort, proxyUser, proxyPassword);
            }
            else {
                ftp = new FTPClient();
            }
        } else {
            FTPSClient ftps;
            if (protocol.equals("true")) {
                ftps = new FTPSClient(true);
            } else if (protocol.equals("false")) {
                ftps = new FTPSClient(false);
            } else {
                String prot[] = protocol.split(",");
                if (prot.length == 1) { // Just protocol
                    ftps = new FTPSClient(protocol);
                } else { // protocol,true|false
                    ftps = new FTPSClient(prot[0], Boolean.parseBoolean(prot[1]));
                }
            }
            ftp = ftps;
            if ("all".equals(trustmgr)) {
                ftps.setTrustManager(TrustManagerUtils.getAcceptAllTrustManager());
            } else if ("valid".equals(trustmgr)) {
                ftps.setTrustManager(TrustManagerUtils.getValidateServerCertificateTrustManager());
            } else if ("none".equals(trustmgr)) {
                ftps.setTrustManager(null);
            }
        }

        if (printHash) {
            ftp.setCopyStreamListener(createListener());
        }
        if (keepAliveTimeout >= 0) {
            ftp.setControlKeepAliveTimeout(keepAliveTimeout);
        }
        if (controlKeepAliveReplyTimeout >= 0) {
            ftp.setControlKeepAliveReplyTimeout(controlKeepAliveReplyTimeout);
        }
        if (encoding != null) {
            ftp.setControlEncoding(encoding);
        }
        ftp.setListHiddenFiles(hidden);

        // suppress login details
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));

        final FTPClientConfig config;
        if (serverType != null) {
            config = new FTPClientConfig(serverType);
        } else {
            config = new FTPClientConfig();
        }
        config.setUnparseableEntries(saveUnparseable);
        if (defaultDateFormat != null) {
            config.setDefaultDateFormatStr(defaultDateFormat);
        }
        if (recentDateFormat != null) {
            config.setRecentDateFormatStr(recentDateFormat);
        }
        ftp.configure(config);

        try
        {
            int reply;
            if (port > 0) {
                ftp.connect(server, port);
            } else {
                ftp.connect(server);
            }
            System.out.println("Connected to " + server + " on " + (port>0 ? port : ftp.getDefaultPort()));

            // After connection attempt, you should check the reply code to verify
            // success.
            reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply))
            {
                ftp.disconnect();
                console.external.print("FTP server refused connection.");
                System.exit(1);
            }
        }
        catch (IOException e)
        {
            if (ftp.isConnected())
            {
                try
                {
                    ftp.disconnect();
                }
                catch (IOException f)
                {
                    // do nothing
                }
            }
            console.external.print("Could not connect to server.");
            e.printStackTrace();
            System.exit(1);
        }

__main:
        try
        {
            if (!ftp.login(username, password))
            {
                ftp.logout();
                error = true;
                break __main;
            }

            System.out.println("Remote system is " + ftp.getSystemType());

            if (binaryTransfer) {
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
            } else {
                // in theory this should not be necessary as servers should default to ASCII
                // but they don't all do so - see NET-500
                ftp.setFileType(FTP.ASCII_FILE_TYPE);
            }

            // Use passive mode as default because most of us are
            // behind firewalls these days.
            if (localActive) {
                ftp.enterLocalActiveMode();
            } else {
                ftp.enterLocalPassiveMode();
            }

            ftp.setUseEPSVwithIPv4(useEpsvWithIPv4);

            if (storeFile)
            {
                InputStream input;

                input = new FileInputStream(local);

                ftp.storeFile(remote, input);

                input.close();
            }
            // Allow multiple list types for single invocation
            else if (listFiles || mlsd || mdtm || mlst || listNames)
            {
                if (mlsd) {
                    for (FTPFile f : ftp.mlistDir(remote)) {
                        System.out.println(f.getRawListing());
                        System.out.println(f.toFormattedString(displayTimeZoneId));
                    }
                }
                if (mdtm) {
                    FTPFile f = ftp.mdtmFile(remote);
                    if (f != null) {
                        System.out.println(f.getRawListing());
                        System.out.println(f.toFormattedString(displayTimeZoneId));
                    } else {
                        System.out.println("File not found");
                    }
                }
                if (mlst) {
                    FTPFile f = ftp.mlistFile(remote);
                    if (f != null){
                        System.out.println(f.toFormattedString(displayTimeZoneId));
                    }
                }
                if (listNames) {
                    for (String s : ftp.listNames(remote)) {
                        System.out.println(s);
                    }
                }
                // Do this last because it changes the client
                if (listFiles) {
                    if (lenient || serverTimeZoneId != null) {
                        config.setLenientFutureDates(lenient);
                        if (serverTimeZoneId != null) {
                            config.setServerTimeZoneId(serverTimeZoneId);
                        }
                        ftp.configure(config );
                    }

                    for (FTPFile f : ftp.listFiles(remote)) {
                        System.out.println(f.getRawListing());
                        System.out.println(f.toFormattedString(displayTimeZoneId));
                    }
                }
            }
            else if (feat)
            {
                // boolean feature check
                if (remote != null) { // See if the command is present
                    if (ftp.hasFeature(remote)) {
                        System.out.println("Has feature: "+remote);
                    } else {
                        if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                            System.out.println("FEAT "+remote+" was not detected");
                        } else {
                            System.out.println("Command failed: "+ftp.getReplyString());
                        }
                    }

                    // Strings feature check
                    String []features = ftp.featureValues(remote);
                    if (features != null) {
                        for(String f : features) {
                            System.out.println("FEAT "+remote+"="+f+".");
                        }
                    } else {
                        if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                            System.out.println("FEAT "+remote+" is not present");
                        } else {
                            System.out.println("Command failed: "+ftp.getReplyString());
                        }
                    }
                } else {
                    if (ftp.features()) {
//                        Command listener has already printed the output
                    } else {
                        System.out.println("Failed: "+ftp.getReplyString());
                    }
                }
            }
            else if (doCommand != null)
            {
                if (ftp.doCommand(doCommand, remote)) {
//                  Command listener has already printed the output
//                    for(String s : ftp.getReplyStrings()) {
//                        System.out.println(s);
//                    }
                } else {
                    System.out.println("Failed: "+ftp.getReplyString());
                }
            }
            else
            {
                OutputStream output;

                output = new FileOutputStream(local);

                ftp.retrieveFile(remote, output);

                output.close();
            }

            ftp.noop(); // check that control connection is working OK

            ftp.logout();
        }
        catch (FTPConnectionClosedException e)
        {
            error = true;
            console.external.print("Server closed connection.");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            error = true;
            e.printStackTrace();
        }
        finally
        {
            if (ftp.isConnected())
            {
                try
                {
                    ftp.disconnect();
                }
                catch (IOException f)
                {
                    // do nothing
                }
            }
        }

        System.exit(error ? 1 : 0);
    } // end main

    private static CopyStreamListener createListener(){
        return new CopyStreamListener(){
            private long megsTotal = 0;

            @Override
            public void bytesTransferred(CopyStreamEvent event) {
                bytesTransferred(event.getTotalBytesTransferred(), event.getBytesTransferred(), event.getStreamSize());
            }

            @Override
            public void bytesTransferred(long totalBytesTransferred,
                    int bytesTransferred, long streamSize) {
                long megs = totalBytesTransferred / 1000000;
                for (long l = megsTotal; l < megs; l++) {
                    System.err.print("#");
                }
                megsTotal = megs;
          }
        };
    }
}
