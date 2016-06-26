package com.yinrong.tools.util;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.yinrong.tools.OpenConstants;



public class FileUtil {
	private static final int BUFFER_SIZE = 16 * 1024;
	/**
	 * 写Txt文件内容
	 * 
	 * @param targetDir
	 *            文件目录
	 * @param fileName
	 *            文件名称
	 * @param content
	 *            文件内容
	 * @param encoding
	 *            文件编码格式 默认为utf-8
	 */
	public static void doTxtFile(String targetDir, String fileName, String content, String encoding) {
		File file;
		try {
			makeMoreDir(targetDir);
			file = new File("");
			file = new File(targetDir + fileName);
			if (file.exists()) {// 如果文件存在删除重新建
				file.delete();
			}
			writeFile(file, content, encoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 拷贝文件
	 * @param src 源文件
	 * @param dst 目标文件
	 */
	public static void copy(File src, File dst) {
	    InputStream in = null;
	    OutputStream out = null;
	    try {
	        in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
	        out = new BufferedOutputStream(new FileOutputStream(dst),
	                BUFFER_SIZE);
	        byte[] buffer = new byte[BUFFER_SIZE];
	        int len = 0;
	        while ((len = in.read(buffer)) > 0) {
	            out.write(buffer, 0, len);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (null != in) {
	            try {
	                in.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        if (null != out) {
	            try {
	                out.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

	/** 
	 * 这里用一句话描述这个方法的作用
	 *
	 * @param file
	 * @param content
	 * @param encoding
	 * @param append	是否追加
	 * @return 
	 * @since JDK 1.6 
	 */  
	public static int writeFile(File file, String content, String encoding, boolean append){
		FileOutputStream fos=null;
		OutputStreamWriter osw=null;
		if (StringUtils.isBlank(encoding)) {// 编码格式默认为UTF_8
			encoding = OpenConstants.CHARSET_UTF8;
		}
		try {
			fos = new FileOutputStream(file, append);
			osw = new OutputStreamWriter(fos, encoding);
			osw.write(content);
			osw.flush();
			return 1;
		} catch (Exception e) {
			return -1;
		}finally{
			try {
				osw.close();
				fos.close();
			} catch (IOException e) {
			}
			
		}
	}
	/**
	 * 写文件，默认是UTF-8的格式
	 * 
	 * @param file
	 *            文件
	 * @param content
	 *            写入文件内容
	 * @param encoding
	 *            文件编码格式
	 * @return
	 */
	public static int writeFile(File file, String content, String encoding) {
		return writeFile(file, content, encoding, false);
	}
	/**
	 * 读图片文件
	 * @param file 目标文件
	 * @param encoding 目标文件编码格式
	 * @return 图片
	 */
	public static Image readImage(File file, String encoding) {
		Image   src=null;
		try {
			src   =   javax.imageio.ImageIO.read(file);
		} catch (IOException e) {
		}   //构造Image对象
		return src;
	}
	/**
	 * 取得文件记录数
	 * 
	 * @param filePathName
	 *            文件路径+文件名称
	 * @return
	 */
	public static int getTxtFileNum(String filePathName, String encoding) {
		if (StringUtils.isBlank(encoding)) {// 编码格式默认为UTF_8
			encoding = OpenConstants.CHARSET_UTF8;
		}
		int iFileSize = 0;
		String s = null;
		File file = new File(filePathName);
		try {
			if (file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader br = new BufferedReader(read);
				while ((s = br.readLine()) != null && !"".equals(s)) {
					iFileSize++;
				}
				br.close();
				read.close();
			}
		} catch (Exception e) {
		}
		return iFileSize;
	}

	/**
	 * 读取文件内容
	 * 
	 * @param filePathName
	 *            文件路径+文件名称
	 * @return
	 */
	public static String getTxtFileContent(String filePathName, String encoding) {
		String strContent = "";
		File file = new File(filePathName);
		strContent = readFile(file, encoding);
		return strContent;
	}

	/**
	 * 按行读文件
	 * 
	 * @param file
	 *            文件
	 * @param encoding
	 *            文件编码格式 默认utf-8
	 * @return
	 */
	public static String readFile(File file, String encoding) {
		if (StringUtils.isBlank(encoding)) {// 编码格式默认为UTF_8
			encoding = OpenConstants.CHARSET_UTF8;
		}
		String strContent = "";
		String s = null;
		if (file.exists()) {
			InputStreamReader read=null;
			BufferedReader br=null;
			try {
				read = new InputStreamReader(new FileInputStream(file), encoding);
				br = new BufferedReader(read);
				while ((s = br.readLine()) != null) {
					strContent += s + "\n";
				}
			} catch (Exception e) {
				return strContent;
			}finally{
				try {
					read.close();
					br.close();
				} catch (IOException e) {
				}
				
			}
		}
		return strContent;
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathName
	 *            文件路径+文件名称
	 */
	public static void delFile(String filePathName) {
		File file = new File(filePathName);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * 删除目录及目录下的所有文件
	 * 
	 * @param delFile
	 *            目录路径
	 */
	public static void allDelete(String filePath) {
		try {
			File f = new File(filePath); // 定义文件路径
			if (f.exists() && f.isDirectory()) { // 判断是文件还是目录
				if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
					f.delete();
				} else {// 若有则把文件放进数组，并判断是否有下级目录
					File delFile[] = f.listFiles();
					int i = f.listFiles().length;
					for (int j = 0; j < i; j++) {
						if (delFile[j].isDirectory()) {
							allDelete(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
						}
						delFile[j].delete();// 删除文件
					}
				}
				f.delete();
			}
		} catch (Exception e) {
		}
	}
	@SuppressWarnings({ "rawtypes" })
	public static List getSubFilesName(String baseDir) {
		File f = new File(baseDir); 
		return getSubFilesName(f);
	}
	/**
	 * 取得指定目录下的所有文件列表，包括子目录
	 * 
	 * @param baseDir
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getSubFilesName(File baseDir) {
		try {
			List ret = new ArrayList();
			File[] tmp = baseDir.listFiles();
			for (int i = 0; i < tmp.length; i++) {
				if (tmp[i].isFile()) {
					ret.add(tmp[i].getName());
				}
				if (tmp[i].isDirectory()) {
					ret.addAll(getSubFilesName(tmp[i]));
				}
			}
			return ret;
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 取得指定目录下的所有文件列表，包括子目录
	 * 
	 * @param baseDir
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getSubFiles(File baseDir) {
		try {
			List ret = new ArrayList();
			File[] tmp = baseDir.listFiles();
			for (int i = 0; i < tmp.length; i++) {
				if (tmp[i].isFile()) {
					ret.add(tmp[i]);
				}
				if (tmp[i].isDirectory()) {
					ret.addAll(getSubFiles(tmp[i]));
				}
			}
			return ret;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 复制文件。targetFile为目标文件，file为源文件
	 * 
	 * @param targetFile
	 *            目标文件
	 * @param file
	 *            源文件
	 * @param targetEncoding
	 *            目标编码格式
	 * @param encoding
	 *            源编码格式
	 * @throws IOException 
	 */
	public static void copyFile( File file,File targetFile, String targetEncoding, String encoding) {
//		if (targetFile.exists()) {
//			targetFile.delete();
//			return;
//		} else {
//			createFile(targetFile, true);
//		}
		targetFile.delete();
		createFile(targetFile, true);
		String exStr = targetFile.getAbsolutePath().substring(targetFile.getAbsolutePath().lastIndexOf(".") + 1);
		if (exStr.equals("jpg") || exStr.equals("png") || exStr.equals("gif") || exStr.equals("bmp") || exStr.equals("jpeg")) {
			FileUtil.upLoadPicFile(file,targetFile,1024);
		}else {
			FileUtil.copy(file,targetFile);
			/*
			FileInputStream fis = null; 
			FileOutputStream fos = null; 
			try {
				//String content = readFile(file, encoding);
				//writeFile(targetFile, content, targetEncoding);
				fis = new FileInputStream(file); 
				fos = new FileOutputStream(targetFile); 
				byte[] buffer = new byte[BUFFER_SIZE]; 
				int len; 
				while ((len = fis.read(buffer)) != -1) { 
					fos.write(buffer, 0, len); 
				}
			} catch (Exception e) {
			} finally {
				try {
					fos.close();
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}*/
		}
	}
	
	public static void copyFile(String filepath,String targetfilepath,String targetEncoding, String encoding){
		copyFile(new File(filepath), new File(targetfilepath), targetEncoding, encoding);
	}
	/**
	 * 拷贝一个目录或者文件到指定路径下
	 * 
	 * @param source
	 *            原文件
	 * @param target
	 *            目标地址
	 */
	public static void copy(File source, File target, String encoding, String targetEncoding) {
		File tarpath = new File(target, source.getName());
		if (tarpath.exists()) {
			tarpath.delete();
		}
		if (source.isDirectory()) {
			tarpath.mkdir();
			File[] dir = source.listFiles();
			for (int i = 0; i < dir.length; i++) {
				copy(dir[i], tarpath,encoding,targetEncoding);
			}
		} else {
			copyFile(source, target, encoding, targetEncoding);
		}

	}

	/**
	 * 创建多级目录
	 * 
	 * @param path
	 */
	public static void makeMoreDir(String path) {
		StringTokenizer st = new StringTokenizer(path, System.getProperties().getProperty("file.separator"));
		String path1 = st.nextToken() + System.getProperties().getProperty("file.separator");
		String path2 = path1;
		while (st.hasMoreTokens()) {
			path1 = st.nextToken() + System.getProperties().getProperty("file.separator");
			path2 += path1;
			File inbox = new File(path2);
			if (!inbox.exists()) {
				inbox.mkdir();
			}
		}
	}

//	/**
//	 * Html文件复制
//	 * 
//	 * @param urlPath
//	 *            html路径
//	 * @param urlName
//	 *            html文件名称
//	 */
//	public static String copyHtml(String urlPath, String urlName, String type,String na) {
//		// 文件原地址
//		String resouFile = ResourceBundleUtil.getBasePath() + urlPath + urlName;
//		// 目标地址
//		String targetDir = ResourceBundleUtil.getBasePath() + ResourceBundleUtil.getPublishPath(type);
//		makeMoreDir(targetDir);
//		copy(new File(resouFile), new File(targetDir));
//		delFile(resouFile);
//		return ResourceBundleUtil.getPublishPath(type);
//	}
	/**
	 * 图片文件上传
	 * 
	 * @return
	 */
	public static void upLoadPicFile(File src, File dst, int buffer_size) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src), buffer_size);
				out = new BufferedOutputStream(new FileOutputStream(dst), buffer_size);
				byte[] buffer = new byte[buffer_size];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图片文件上传
	 * 
	 * @return
	 */
	public static void upLoadPicFileByStream(InputStream in , File dst, int buffer_size) {
		try {
			OutputStream out = null;
			try {
				out = new BufferedOutputStream(new FileOutputStream(dst), buffer_size);
				byte[] buffer = new byte[buffer_size];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 图片文件上传
	 * 
	 * @return
	 */
	public static void upLoadPicFile(File src, String dstpath, int buffer_size) {
		makeMoreDir(dstpath);
		File dst = new File(dstpath);
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src), buffer_size);
				out = new BufferedOutputStream(new FileOutputStream(dst), buffer_size);
				byte[] buffer = new byte[buffer_size];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取路径下的文件
	 * @param dir
	 * @return
	 */
	public static String[] listFile(File dir) {
		String absolutPath = dir.getAbsolutePath();
		String[] paths = dir.list();
		String[] files = new String[paths.length];
		for (int i = 0; i < paths.length; i++) {
			files[i] = absolutPath + System.getProperties().getProperty("file.separator") + paths[i];
		}
		return files;
	}

	/**
	 * 创建文件
	 * @param path
	 * @param isFile
	 */
	public static void createFile(String path, boolean isFile) {
		createFile(new File(path), isFile);
	}

	/**
	 * 创建文件
	 * @param file
	 * @param isFile
	 */
	public static void createFile(File file, boolean isFile) {
		if(! file.exists()) {  
			makeDir(file.getParentFile());  
	    }  
	   try {
		   file.createNewFile();
	   } catch (IOException e) {
		   e.printStackTrace();
	   } 
	}

	public static void makeDir(File dir) {  
       if(! dir.getParentFile().exists()) {  
           makeDir(dir.getParentFile());  
       }  
       dir.mkdir();  
	}  
	
	/** 
	 *  根据路径删除指定的目录或文件，无论存在与否 
	 *@param sPath  要删除的目录或文件 
	 *@return 删除成功返回 true，否则返回 false。 
	 */  
	public static boolean DeleteFolder(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 判断目录或文件是否存在  
	    if (!file.exists()) {  // 不存在返回 false  
	        return flag;  
	    } else {  
	        // 判断是否为文件  
	        if (file.isFile()) {  // 为文件时调用删除文件方法  
	            return deleteFile(sPath);  
	        } else {  // 为目录时调用删除目录方法  
	            return deleteDirectory(sPath);  
	        }  
	    }  
	} 
	
    /** 
     * 删除单个文件 
     * @param   sPath    被删除文件的文件名 
     * @return 单个文件删除成功返回true，否则返回false 
     */  
    public static boolean deleteFile(String sPath) {  
    	boolean flag = false;  
    	File file = new File(sPath);  
        // 路径为文件且不为空则进行删除  
        if (file.isFile() && file.exists()) {  
            file.delete();  
            flag = true;  
        }  
        return flag;  
    }  
    
    /** 
     * 删除目录（文件夹）以及目录下的文件 
     * @param   sPath 被删除目录的文件路径 
     * @return  目录删除成功返回true，否则返回false 
     */  
    public static boolean deleteDirectory(String sPath) {  
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
        if (!sPath.endsWith(File.separator)) {  
            sPath = sPath + File.separator;  
        }  
        File dirFile = new File(sPath);  
        //如果dir对应的文件不存在，或者不是一个目录，则退出  
        if (!dirFile.exists() || !dirFile.isDirectory()) {  
            return false;  
        }  
        boolean flag = true;  
        //删除文件夹下的所有文件(包括子目录)  
        File[] files = dirFile.listFiles();  
        for (int i = 0; i < files.length; i++) {  
            //删除子文件  
            if (files[i].isFile()) {  
                flag = deleteFile(files[i].getAbsolutePath());  
                if (!flag) break;  
            } //删除子目录  
            else {  
                flag = deleteDirectory(files[i].getAbsolutePath());  
                if (!flag) break;  
            }  
        }  
        if (!flag) return false;  
        //删除当前目录  
        if (dirFile.delete()) {  
            return true;  
        } else {  
            return false;  
        }  
    }  

	/**
	 * 根据路径获取文件名称
	 * @param path
	 * @return
	 */
	public static String getFileName(String path){
		String name="";
		if(StringUtils.isNotBlank(path)){
			String[] str=path.split("\\\\");
			if(null==str||str.length==0){
				str=path.split("/");
			}
			if(null!=str&&str.length!=0){
				str=str[str.length-1].split("/");
				name=str[str.length-1];
			}else{
				name=path;
			}
		}
		return name;
	}
	/**
	 * 获取文件流
	 * @param path
	 * @return
	 */
	public static InputStream getFile(String path){
		InputStream inStream = null;
		try{
			File file = new File(path);
			/* 如果文件存在 */
			if (file.exists()) {
				int fileLength = (int) file.length();
				/* 如果文件长度大于0 */
				if (fileLength != 0) {
					/* 创建输入流 */
					inStream = new FileInputStream(file);
				}
			}
		}catch(Exception e){
			
		}
		return inStream;
	}
	public static void main(String[] args) {
		System.out.println(getAppPath(DateUtil.class));
		
	}
	public static String getAppPath(Class<?> cls) {  
        //检查用户传入的参数是否为空   
        if (cls == null)  
            throw new java.lang.IllegalArgumentException("参数不能为空！");  
        ClassLoader loader = cls.getClassLoader();  
        //获得类的全名，包括包名   
        String clsName = cls.getName() + ".class";  
        //获得传入参数所在的包   
        Package pack = cls.getPackage();  
        String path = "";  
        //如果不是匿名包，将包名转化为路径   
        if (pack != null) {  
            String packName = pack.getName();  
            //此处简单判定是否是Java基础类库，防止用户传入JDK内置的类库   
            if (packName.startsWith("java.") || packName.startsWith("javax."))  
                throw new java.lang.IllegalArgumentException("不要传送系统类！");  
            //在类的名称中，去掉包名的部分，获得类的文件名   
            clsName = clsName.substring(packName.length() + 1);  
            //判定包名是否是简单包名，如果是，则直接将包名转换为路径，   
            if (packName.indexOf(".") < 0)  
                path = packName + "/";  
            else {//否则按照包名的组成部分，将包名转换为路径   
                int start = 0, end = 0;  
                end = packName.indexOf(".");  
                while (end != -1) {  
                    path = path + packName.substring(start, end) + "/";  
                    start = end + 1;  
                    end = packName.indexOf(".", start);  
                }  
                path = path + packName.substring(start) + "/";  
            }  
        }  
         
        //调用ClassLoader的getResource方法，传入包含路径信息的类文件名   
        java.net.URL url = loader.getResource(path + clsName);  
        //从URL对象中获取路径信息   
         
        String realPath = url.getPath();  
        //去掉路径信息中的协议名"file:"   
        int pos = realPath.indexOf("file:");  
        if (pos > -1)  
            realPath = realPath.substring(pos + 5);  
         
         
        //去掉路径信息最后包含类文件信息的部分，得到类所在的路径   
        pos = realPath.indexOf(path + clsName);  
        realPath = realPath.substring(0, pos - 1);  
         
        //如果类文件被打包到JAR等文件中时，去掉对应的JAR等打包文件名   
        if (realPath.endsWith("!"))  
            realPath = realPath.substring(0, realPath.lastIndexOf("/"));  
         
         
        //结果字符串可能因平台默认编码不同而不同。因此，改用 decode(String,String) 方法指定编码。   
        try {  
            realPath = java.net.URLDecoder.decode(realPath, "utf-8");  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
        System.out.println(realPath);  
        return realPath;  
    }
}
