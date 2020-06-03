using System;
using System.Collections.Generic;
using ExperimentsData.Models.DTO;

namespace ExperimentsData.Services
{
    public interface IDatasetService
    {
        List<DatasetListDTO> getAll();
        DatasetRegisterDTO Create(DatasetRegisterDTO datasetEntity);
        DatasetRegisterDTO getById(Guid guid);
        byte[] DownloadFileById(Guid guid);
        DatasetListDTO getByName(string name);
        DatasetRegisterDTO deleteById(Guid guid);
        DatasetRegisterDTO Update(DatasetRegisterDTO registerDto);
    }
}