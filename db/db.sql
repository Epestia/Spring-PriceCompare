USE [master]
GO
/****** Object:  Database [PriceCompare]    Script Date: 22/11/2024 20:04:23 ******/
CREATE DATABASE [PriceCompare]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PriceCompare', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\PriceCompare.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PriceCompare_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\PriceCompare_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [PriceCompare] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PriceCompare].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PriceCompare] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PriceCompare] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PriceCompare] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PriceCompare] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PriceCompare] SET ARITHABORT OFF 
GO
ALTER DATABASE [PriceCompare] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PriceCompare] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PriceCompare] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PriceCompare] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PriceCompare] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PriceCompare] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PriceCompare] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PriceCompare] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PriceCompare] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PriceCompare] SET  ENABLE_BROKER 
GO
ALTER DATABASE [PriceCompare] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PriceCompare] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PriceCompare] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PriceCompare] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PriceCompare] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PriceCompare] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PriceCompare] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PriceCompare] SET RECOVERY FULL 
GO
ALTER DATABASE [PriceCompare] SET  MULTI_USER 
GO
ALTER DATABASE [PriceCompare] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PriceCompare] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PriceCompare] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PriceCompare] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PriceCompare] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [PriceCompare] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'PriceCompare', N'ON'
GO
ALTER DATABASE [PriceCompare] SET QUERY_STORE = ON
GO
ALTER DATABASE [PriceCompare] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [PriceCompare]
GO
/****** Object:  Table [dbo].[ArticleEntity]    Script Date: 22/11/2024 20:04:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ArticleEntity](
	[ArticleID] [int] IDENTITY(1,1) NOT NULL,
	[ArticleName] [nvarchar](255) NOT NULL,
	[ArticleDefaultPrice] [decimal](10, 2) NOT NULL,
	[LastUpdated] [datetime] NULL,
	[UnitID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ArticleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[exportsEntity]    Script Date: 22/11/2024 20:04:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[exportsEntity](
	[ExportID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[ExportDate] [datetime] NULL,
	[ExportType] [nvarchar](50) NULL,
	[ExportData] [varbinary](max) NULL,
	[ArticleID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ExportID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[notificationsEntity]    Script Date: 22/11/2024 20:04:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[notificationsEntity](
	[NotificationID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[ArticleID] [int] NOT NULL,
	[StoreID] [int] NOT NULL,
	[NotificationDate] [datetime] NULL,
	[NotificationType] [nvarchar](50) NULL,
	[IsRead] [bit] NULL,
	[NotificationMessage] [nvarchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[NotificationID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PriceEntity]    Script Date: 22/11/2024 20:04:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PriceEntity](
	[PriceID] [int] IDENTITY(1,1) NOT NULL,
	[StoreID] [int] NOT NULL,
	[StartDate] [date] NOT NULL,
	[EndDate] [date] NULL,
	[Price] [decimal](10, 2) NOT NULL,
	[PreviousPrice] [decimal](10, 2) NULL,
	[IsPromotion] [bit] NULL,
	[IsEstimatedPrice] [bit] NULL,
	[ArticleID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[PriceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[priceReportsEntity]    Script Date: 22/11/2024 20:04:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[priceReportsEntity](
	[ReportID] [int] IDENTITY(1,1) NOT NULL,
	[ArticleID] [int] NOT NULL,
	[StoreID] [int] NOT NULL,
	[UserID] [int] NOT NULL,
	[ReportedPrice] [decimal](10, 2) NOT NULL,
	[ReportDate] [date] NOT NULL,
	[ProofImage] [varbinary](max) NULL,
	[IsValidated] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[ReportID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RoleEntity]    Script Date: 22/11/2024 20:04:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RoleEntity](
	[RoleID] [int] IDENTITY(1,1) NOT NULL,
	[RoleName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RolesEntity]    Script Date: 22/11/2024 20:04:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RolesEntity](
	[UserID] [int] NOT NULL,
	[RoleID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC,
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[StoreEntity]    Script Date: 22/11/2024 20:04:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StoreEntity](
	[StoreID] [int] IDENTITY(1,1) NOT NULL,
	[StoreName] [nvarchar](255) NOT NULL,
	[StoreCity] [nvarchar](200) NOT NULL,
	[StoreAddress] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[StoreID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UnitEntity]    Script Date: 22/11/2024 20:04:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UnitEntity](
	[UnitID] [int] IDENTITY(1,1) NOT NULL,
	[UnitName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UnitID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserEntity]    Script Date: 22/11/2024 20:04:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserEntity](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [nvarchar](200) NOT NULL,
	[LastName] [nvarchar](200) NOT NULL,
	[City] [nvarchar](200) NOT NULL,
	[BirthDate] [date] NOT NULL,
	[Mail] [nvarchar](200) NOT NULL,
	[PasswordHash] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[ArticleEntity] ON 

INSERT [dbo].[ArticleEntity] ([ArticleID], [ArticleName], [ArticleDefaultPrice], [LastUpdated], [UnitID]) VALUES (1, N'string', CAST(0.00 AS Decimal(10, 2)), CAST(N'2024-11-18T14:49:07.313' AS DateTime), 1)
INSERT [dbo].[ArticleEntity] ([ArticleID], [ArticleName], [ArticleDefaultPrice], [LastUpdated], [UnitID]) VALUES (2, N'string', CAST(0.00 AS Decimal(10, 2)), CAST(N'2024-11-19T19:10:33.610' AS DateTime), 1)
INSERT [dbo].[ArticleEntity] ([ArticleID], [ArticleName], [ArticleDefaultPrice], [LastUpdated], [UnitID]) VALUES (3, N'string', CAST(0.00 AS Decimal(10, 2)), CAST(N'2024-11-21T23:53:16.347' AS DateTime), 1)
INSERT [dbo].[ArticleEntity] ([ArticleID], [ArticleName], [ArticleDefaultPrice], [LastUpdated], [UnitID]) VALUES (4, N'string', CAST(0.00 AS Decimal(10, 2)), CAST(N'2024-11-21T23:53:16.347' AS DateTime), 1)
INSERT [dbo].[ArticleEntity] ([ArticleID], [ArticleName], [ArticleDefaultPrice], [LastUpdated], [UnitID]) VALUES (5, N'livres', CAST(3.96 AS Decimal(10, 2)), CAST(N'2024-11-22T10:10:57.477' AS DateTime), 3)
INSERT [dbo].[ArticleEntity] ([ArticleID], [ArticleName], [ArticleDefaultPrice], [LastUpdated], [UnitID]) VALUES (6, N'Manga', CAST(7.55 AS Decimal(10, 2)), CAST(N'2024-11-21T22:58:12.763' AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[ArticleEntity] OFF
GO
SET IDENTITY_INSERT [dbo].[PriceEntity] ON 

INSERT [dbo].[PriceEntity] ([PriceID], [StoreID], [StartDate], [EndDate], [Price], [PreviousPrice], [IsPromotion], [IsEstimatedPrice], [ArticleID]) VALUES (7, 2, CAST(N'2024-11-22' AS Date), CAST(N'2024-11-22' AS Date), CAST(3.58 AS Decimal(10, 2)), CAST(5.20 AS Decimal(10, 2)), 1, 1, 5)
SET IDENTITY_INSERT [dbo].[PriceEntity] OFF
GO
SET IDENTITY_INSERT [dbo].[RoleEntity] ON 

INSERT [dbo].[RoleEntity] ([RoleID], [RoleName]) VALUES (2, N'ADMIN')
INSERT [dbo].[RoleEntity] ([RoleID], [RoleName]) VALUES (3, N'SUPER_ADMIN')
INSERT [dbo].[RoleEntity] ([RoleID], [RoleName]) VALUES (1, N'USER')
SET IDENTITY_INSERT [dbo].[RoleEntity] OFF
GO
INSERT [dbo].[RolesEntity] ([UserID], [RoleID]) VALUES (2, 2)
INSERT [dbo].[RolesEntity] ([UserID], [RoleID]) VALUES (12, 1)
INSERT [dbo].[RolesEntity] ([UserID], [RoleID]) VALUES (1002, 1)
GO
SET IDENTITY_INSERT [dbo].[StoreEntity] ON 

INSERT [dbo].[StoreEntity] ([StoreID], [StoreName], [StoreCity], [StoreAddress]) VALUES (1, N'string', N'string', N'string')
INSERT [dbo].[StoreEntity] ([StoreID], [StoreName], [StoreCity], [StoreAddress]) VALUES (2, N'Supermarché', N'Paris', N'123 Rue Principale')
INSERT [dbo].[StoreEntity] ([StoreID], [StoreName], [StoreCity], [StoreAddress]) VALUES (3, N'Coram', N'La louviere', N'rue coincoin')
SET IDENTITY_INSERT [dbo].[StoreEntity] OFF
GO
SET IDENTITY_INSERT [dbo].[UnitEntity] ON 

INSERT [dbo].[UnitEntity] ([UnitID], [UnitName]) VALUES (3, N'Kilo')
INSERT [dbo].[UnitEntity] ([UnitID], [UnitName]) VALUES (4, N'Litre')
INSERT [dbo].[UnitEntity] ([UnitID], [UnitName]) VALUES (1, N'Pièce')
SET IDENTITY_INSERT [dbo].[UnitEntity] OFF
GO
SET IDENTITY_INSERT [dbo].[UserEntity] ON 

INSERT [dbo].[UserEntity] ([UserID], [FirstName], [LastName], [City], [BirthDate], [Mail], [PasswordHash]) VALUES (2, N'mihawk', N'memik', N'mons', CAST(N'2024-11-19' AS Date), N'mihawk.mekd@hotmail.com', N'zezefzfezzfzfzfzfzafzefsegser')
INSERT [dbo].[UserEntity] ([UserID], [FirstName], [LastName], [City], [BirthDate], [Mail], [PasswordHash]) VALUES (12, N'Alice', N'Durand', N'Brusseefeffls', CAST(N'1990-07-14' AS Date), N'alice.durand@example.com', N'newpasswordhashed123')
INSERT [dbo].[UserEntity] ([UserID], [FirstName], [LastName], [City], [BirthDate], [Mail], [PasswordHash]) VALUES (1002, N'Lara', N'Close', N'thionville', CAST(N'2024-11-22' AS Date), N'Lara.close@hotmail.com', N'Lokijipopo7141')
SET IDENTITY_INSERT [dbo].[UserEntity] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__RoleEnti__8A2B61602AA0E0DB]    Script Date: 22/11/2024 20:04:23 ******/
ALTER TABLE [dbo].[RoleEntity] ADD UNIQUE NONCLUSTERED 
(
	[RoleName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__StoreEnt__520DB65226BB71E9]    Script Date: 22/11/2024 20:04:23 ******/
ALTER TABLE [dbo].[StoreEntity] ADD UNIQUE NONCLUSTERED 
(
	[StoreName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__UnitEnti__B5EE6678025D8C97]    Script Date: 22/11/2024 20:04:23 ******/
ALTER TABLE [dbo].[UnitEntity] ADD UNIQUE NONCLUSTERED 
(
	[UnitName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__UserEnti__2724B2D185ED39B5]    Script Date: 22/11/2024 20:04:23 ******/
ALTER TABLE [dbo].[UserEntity] ADD UNIQUE NONCLUSTERED 
(
	[Mail] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ArticleEntity] ADD  DEFAULT (getdate()) FOR [LastUpdated]
GO
ALTER TABLE [dbo].[ArticleEntity] ADD  DEFAULT ((1)) FOR [UnitID]
GO
ALTER TABLE [dbo].[exportsEntity] ADD  DEFAULT (getdate()) FOR [ExportDate]
GO
ALTER TABLE [dbo].[notificationsEntity] ADD  DEFAULT (getdate()) FOR [NotificationDate]
GO
ALTER TABLE [dbo].[notificationsEntity] ADD  DEFAULT ((0)) FOR [IsRead]
GO
ALTER TABLE [dbo].[PriceEntity] ADD  DEFAULT ((0)) FOR [IsPromotion]
GO
ALTER TABLE [dbo].[PriceEntity] ADD  DEFAULT ((0)) FOR [IsEstimatedPrice]
GO
ALTER TABLE [dbo].[priceReportsEntity] ADD  DEFAULT (getdate()) FOR [ReportDate]
GO
ALTER TABLE [dbo].[priceReportsEntity] ADD  DEFAULT ((0)) FOR [IsValidated]
GO
ALTER TABLE [dbo].[ArticleEntity]  WITH CHECK ADD  CONSTRAINT [FK_Article_Unit] FOREIGN KEY([UnitID])
REFERENCES [dbo].[UnitEntity] ([UnitID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ArticleEntity] CHECK CONSTRAINT [FK_Article_Unit]
GO
ALTER TABLE [dbo].[exportsEntity]  WITH CHECK ADD FOREIGN KEY([ArticleID])
REFERENCES [dbo].[ArticleEntity] ([ArticleID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[exportsEntity]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[UserEntity] ([UserID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[notificationsEntity]  WITH CHECK ADD FOREIGN KEY([ArticleID])
REFERENCES [dbo].[ArticleEntity] ([ArticleID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[notificationsEntity]  WITH CHECK ADD FOREIGN KEY([StoreID])
REFERENCES [dbo].[StoreEntity] ([StoreID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[notificationsEntity]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[UserEntity] ([UserID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PriceEntity]  WITH CHECK ADD FOREIGN KEY([StoreID])
REFERENCES [dbo].[StoreEntity] ([StoreID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[priceReportsEntity]  WITH CHECK ADD FOREIGN KEY([ArticleID])
REFERENCES [dbo].[ArticleEntity] ([ArticleID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[priceReportsEntity]  WITH CHECK ADD FOREIGN KEY([StoreID])
REFERENCES [dbo].[StoreEntity] ([StoreID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[priceReportsEntity]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[UserEntity] ([UserID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[RolesEntity]  WITH CHECK ADD FOREIGN KEY([RoleID])
REFERENCES [dbo].[RoleEntity] ([RoleID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[RolesEntity]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[UserEntity] ([UserID])
ON DELETE CASCADE
GO
USE [master]
GO
ALTER DATABASE [PriceCompare] SET  READ_WRITE 
GO
