using System;
using System.Collections.Generic;
using ExperimentsData.Models.DAO;
using ExperimentsData.Models.DTO;

namespace ExperimentsData.Services
{
    public interface IDatasetService
    {
        List<DatasetListDTO> getAll();
        DatasetEntity Create(DatasetEntity datasetEntity);
        DatasetRegisterDTO getById(Guid guid);
    }
}