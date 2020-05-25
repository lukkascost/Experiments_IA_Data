using System;
using System.Collections.Generic;
using System.IO;
using ExperimentsData.Models.DAO;
using ExperimentsData.Models.DTO;
using Microsoft.AspNetCore.Mvc;

namespace ExperimentsData.Services
{
    public interface IDatasetService
    {
        List<DatasetListDTO> getAll();
        DatasetRegisterDTO Create(DatasetRegisterDTO datasetEntity);
        DatasetRegisterDTO getById(Guid guid);
        byte[] DownloadFileById(Guid guid);
    }
}