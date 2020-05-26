using System;
using System.Collections.Generic;
using ExperimentsData.Models.DAO;

namespace ExperimentsData.Repositories
{
    public interface ISampleRepository
    {
        List<SampleGroupedEntity> GetAllGrouped(Guid datasetGuid);
        void Save(SampleEntity map);
        SampleEntity GetById(Guid sampleId);
        SampleEntity GetDatabaseIdAndFileName(Guid datasetGuid, string fileName);
    }
}