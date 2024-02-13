package com.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;

public class FileUtil
{
	/**
	 * 파일이 존재하는지 검사
	 *
	 * @param filePath
	 *            파일물리경로
	 * @return
	 */
	public static boolean existFile( String filePath )
	{
		return new File( filePath ).exists();
	}

	/**
	 * 파일 생성
	 *
	 * @param filePath
	 *            파일물리경로
	 * @return
	 * @throws Exception
	 */
	public static File createFile( String filePath ) throws Exception
	{
		File ff = new File( filePath.substring( 0, filePath.lastIndexOf( "/" ) ) );
		ff.mkdirs();
		File f = new File( filePath );
		f.createNewFile();

		return f;
	}

	/**
	 * 폴더 생성
	 *
	 * @param folderPath
	 *            폴더물리경로
	 * @throws Exception
	 */
	public static void createFolder( String folderPath )
	{
		File ff = new File( folderPath.substring( 0, folderPath.lastIndexOf( "/" ) ) );
		ff.mkdirs();
	}

	/**
	 * 파일 읽기
	 *
	 * @param filePath
	 *            파일물리경로
	 * @return
	 * @throws Exception
	 */
	public static String readFile( String filePath ) throws Exception
	{
		File f = new File( filePath );
		FileReader fr = new FileReader( f );
		BufferedReader br = new BufferedReader( fr );

		String s = null;
		StringBuffer sb = new StringBuffer();
		while( ( s = br.readLine() ) != null )
		{
			sb.append( s + "\n" );
		}

		fr.close();
		br.close();

		return sb.toString();
	}

	public static StringBuffer readBytesFile( String filePath ) throws Exception
	{
		StringBuffer sb = new StringBuffer();

		File f = new File( filePath );
		int fLen = (int)f.length();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f),"ISO_8859_1"));
		
		char charArray[] = new char[fLen];
		int i = 0;
		int c = 0;
		while((c = br.read()) != -1 && i < fLen) {
			charArray[i++] = (char)c;
		}
		sb.append(charArray);
		br.close();
		
		return sb;
	}

	/**
	 * 파일쓰기
	 *
	 * @param filePath
	 *            파일물리경로
	 * @param contents
	 *            내용
	 * @throws IOException
	 */
	public static void writeFile( String filePath, String contents ) throws IOException
	{
		try
		{
			File f = new File( filePath );
			BufferedWriter bw = null;
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"ISO_8859_1"));
			bw.write(contents);
			bw.close();
		}
		catch( IOException ioe )
		{
			throw new IOException();
		}
	}

	/**
	 * 파일 삭제
	 *
	 * @param filePath
	 *            파일물리경로
	 */
	public static void deleteFile( String filePath )
	{
		File f = new File( filePath );
		if ( f.exists() )
			f.delete();
	}

	/**
	 * 파일 확장자를 구한다.
	 *
	 * @param fileName
	 * @return
	 */
	public static String getExtend( String fileName )
	{
		String ext = "";
		if( fileName.indexOf( "." ) > -1 )
		{
			String[] arr = fileName.split( "\\." );
			ext = arr[arr.length - 1];
		}

		return ext;
	}

	/**
	 * 파일 복사
	 *
	 * @param orgFile
	 *            원본 파일
	 * @param tarFile
	 *            복사할 파일
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static int copyFile( String orgFile, String tarFile )
	{
		int iRtn = -1;
		FileChannel ic = null;
		FileChannel oc = null;
		try
		{
			File f 	= new File(orgFile);
			if (f.exists()){
				ic = new FileInputStream( orgFile ).getChannel();
				oc = new FileOutputStream( tarFile ).getChannel();
				int maxCount = ( 64 * 1024 * 1024 ) - ( 32 * 1024 );
				long size = ic.size();
				long position = 0;
				while( position < size )
				{
					position += ic.transferTo( position, maxCount, oc );
				}
				iRtn = 1;
			}
			else{
				iRtn = 0;
			}
		}
		catch( IOException e ){}
		finally
		{
			if( ic != null ) try{ic.close();}catch(Exception e){}
			if( oc != null ) try{oc.close();}catch(Exception e){}
		}
		return iRtn;
	}

	/**
	 * 파일 복사
	 *
	 * @param files
	 *            원본 파일
	 * @param targetDir
	 *            복사할 디렉토리
	 * @throws IOException
	 */
	public static void copyFile( File[] files, String targetDir ) throws IOException
	{
		for( int i = 0; i < files.length; i++ )
		{
			File f = new File( files[i].getPath() );

			// 폴더인 경우 하위경로의 폴더 및 파일을 추가
			if( f.isDirectory() )
			{
				File[] subFiles = f.listFiles();
				copyFile( subFiles, targetDir + "/" + f.getName() + "/" );
			}
			else
			{
				createFolder( targetDir );
				copyFile( f.getPath(), targetDir + "/" + files[i].getName() );
			}
		}
	}
}
