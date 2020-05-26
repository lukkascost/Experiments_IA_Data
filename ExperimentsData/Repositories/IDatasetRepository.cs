﻿using System;
using System.Collections.Generic;
using System.Linq;
using ExperimentsData.Models.DAO;
using ExperimentsData.Models.DTO;

namespace ExperimentsData.Repositories
{
    public interface IDatasetRepository
    {
        List<DatasetGroupedEntity> GetAllGrouped();
        void Save(DatasetEntity datasetEntity);
        DatasetEntity GetById(Guid guid);
        DatasetEntity GetByName(string name);
    }
}