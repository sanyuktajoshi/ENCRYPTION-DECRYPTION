package endec;
//package org.sopiro.crypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class encryptFile
{
	private static encryptFile encrypter = new encryptFile();

	private static boolean deleteOriginal;
	
	private encryptFile()
	{
	}

	public static encryptFile getEncrypter(boolean originalFileDeleted)
	{
		deleteOriginal = originalFileDeleted;
		
		return encrypter;
	}

	public void encrypt(String srcpath, String dstpath)
	{
		
		System.out.print(srcpath+"\n");
		File src = new File(srcpath);
		
		System.out.print(dstpath+"\n");
		File dst = new File(dstpath);
		if (!dst.exists())
			dst.mkdir();
		if (!dst.isDirectory())
			return;

		try
		{
			if (!src.isDirectory())
			{
				copyEncrypted(src, dst);
			} else
			{
				File[] files = src.listFiles();

				System.out.println("Encrypting...");

				for (File f : files)
				{
					copyEncrypted(f, dst);
					if(deleteOriginal) f.delete();
				}

				System.out.println(files.length + " files are encrypted");
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void copyEncrypted(File source, File dest) throws IOException
	{
		InputStream is = null;
		OutputStream os = null;

		dest = new File(dest.getPath().concat("/").concat(getRandomName(10, "enc")));

		try
		{
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);

			os.write(new byte[] { (byte) source.getName().length() });
			os.write(stringToByte(source.getName()));

			byte[] buffer = new byte[1024];

			int length;

			while ((length = is.read(buffer)) > 0)
			{
				encryptBytes(buffer);
				os.write(buffer, 0, length);
			}

		} finally
		{
			is.close();
			os.close();
		}
	}

	private void encryptBytes(byte[] data) // Encryption Algorithm is written into here
	{
		for (int i = 0; i < data.length; i++)
		{
			data[i] = (byte) ~data[i];
		}
	}

	public byte[] stringToByte(String data)
	{
		char[] ca = data.toCharArray();
		byte[] res = new byte[ca.length * 2]; // Character.BYTES = 2;

		for (int i = 0; i < res.length; i++)
		{
			res[i] = (byte) ((ca[i / 2] >> (8 - (i % 2) * 8)) & 0xff);
		}

		return res;
	}

	public String getRandomName(int length, String extend)
	{
		Random r = new Random();
		StringBuilder res = new StringBuilder();

		for (int i = 0; i < length; i++)
		{

			char c = 'a';
			int width = 'z' - 'a';

			if (r.nextInt(3) == 0)
			{
				c = 'A';
				width = 'Z' - 'A';
			}
			if (r.nextInt(3) == 1)
			{
				c = '0';
				width = '9' - '0';
			}

			res.append((char) (c + r.nextInt(width)));
		}

		res.append(".").append(extend);

		return res.toString();
	}

	public void copy(File source, File dest) throws IOException
	{
		InputStream is = null;
		OutputStream os = null;

		try
		{
			dest = new File(dest.getPath().concat("/").concat(source.getName()));

			is = new FileInputStream(source);
			os = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;
			int tl = 0;

			while ((length = is.read(buffer)) > 0)
			{
				tl += length;
				os.write(buffer, 0, length);
			}

			System.out.println(tl + " bytes");
		} finally
		{
			is.close();
			os.close();
		}
	}
}